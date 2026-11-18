
package foodordering.order.service;

import foodordering.order.dto.OrderRequestDTO;
import foodordering.order.dto.OrderResponseDTO;
import foodordering.order.dto.OrderItemResponseDTO;
import foodordering.order.entity.Order;
import foodordering.order.entity.OrderItem;
import foodordering.order.enums.OrderStatus;
import foodordering.order.repository.OrderRepository;
import foodordering.order.repository.OrderItemRepository;
import foodordering.menu.entity.MenuItem;
import foodordering.menu.repository.MenuRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final MenuRepository menuRepository;

    public OrderServiceImpl(
            OrderRepository orderRepository,
            OrderItemRepository orderItemRepository,
            MenuRepository menuRepository) {

        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.menuRepository = menuRepository;
    }

    // Places an order after validating items and available stock.
    @Override
    @Transactional
    public OrderResponseDTO placeOrder(OrderRequestDTO requestDTO) {

        // Aggregate quantities in case the same menu item appears more than once.
        Map<Long, Integer> requestedQuantities = new LinkedHashMap<>();

        requestDTO.getItems().forEach(item ->
                requestedQuantities.merge(
                        item.getMenuItemId(),
                        item.getQuantity(),
                        Integer::sum
                )
        );

        Map<Long, MenuItem> menuItems = new LinkedHashMap<>();
        Long restaurantId = null;
        BigDecimal totalAmount = BigDecimal.ZERO;

        // Validate every item before changing any stock.
        for (Map.Entry<Long, Integer> entry : requestedQuantities.entrySet()) {

            MenuItem menuItem = menuRepository.findById(entry.getKey())
                    .orElseThrow(() -> new RuntimeException(
                            "Menu item not found with ID: " + entry.getKey()));

            if (restaurantId == null) {
                restaurantId = menuItem.getRestaurant().getId();
            } else if (!restaurantId.equals(menuItem.getRestaurant().getId())) {
                throw new RuntimeException(
                        "An order can contain items from only one restaurant.");
            }

            if (menuItem.getStockQuantity() < entry.getValue()) {
                throw new RuntimeException(
                        "Insufficient stock for menu item: " + menuItem.getName());
            }

            menuItems.put(entry.getKey(), menuItem);

            totalAmount = totalAmount.add(
                    menuItem.getPrice().multiply(BigDecimal.valueOf(entry.getValue()))
            );
        }

        // Create the order.
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setCustomerName(requestDTO.getCustomerName());
        order.setCustomerEmail(requestDTO.getCustomerEmail());
        order.setCustomerPhone(requestDTO.getCustomerPhone());
        order.setDeliveryAddress(requestDTO.getDeliveryAddress());
        order.setStatus(OrderStatus.PLACED);
        order.setTotalAmount(totalAmount);
        order.setOrderDate(LocalDateTime.now());

        Order savedOrder = orderRepository.save(order);

        // Create order items and reduce stock after all validations pass.
        List<OrderItem> orderItems = new ArrayList<>();

        for (Map.Entry<Long, Integer> entry : requestedQuantities.entrySet()) {

            MenuItem menuItem = menuItems.get(entry.getKey());
            Integer quantity = entry.getValue();

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(savedOrder);
            orderItem.setMenuItem(menuItem);
            orderItem.setQuantity(quantity);
            orderItem.setPurchasePrice(menuItem.getPrice());

            orderItems.add(orderItem);

            menuItem.setStockQuantity(
                    menuItem.getStockQuantity() - quantity
            );

            menuRepository.save(menuItem);
        }

        orderItemRepository.saveAll(orderItems);

        return mapToResponseDTO(savedOrder);
    }

    // Retrieves an order using its ID.
    @Override
    @Transactional(readOnly = true)
    public OrderResponseDTO getOrderById(Long id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Order not found with ID: " + id));

        return mapToResponseDTO(order);
    }

    // Retrieves all orders.
    @Override
    @Transactional(readOnly = true)
    public List<OrderResponseDTO> getAllOrders() {

        return orderRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    // Updates the status of an existing order.
    @Override
    @Transactional
    public OrderResponseDTO updateOrderStatus(Long id, OrderStatus status) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Order not found with ID: " + id));

        if (status == null || !isValidStatusTransition(order.getStatus(), status)) {
            throw new RuntimeException(
                    "Invalid order status transition: "
                            + order.getStatus() + " -> " + status);
        }

        order.setStatus(status);

        Order updatedOrder = orderRepository.save(order);

        return mapToResponseDTO(updatedOrder);
    }

    // Cancels an existing order and restores its stock.
    @Override
    @Transactional
    public OrderResponseDTO cancelOrder(Long id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Order not found with ID: " + id));

        if (order.getStatus() == OrderStatus.CANCELLED
                || order.getStatus() == OrderStatus.DELIVERED) {
            throw new RuntimeException(
                    "This order cannot be cancelled.");
        }

        List<OrderItem> orderItems =
                orderItemRepository.findByOrderId(order.getId());

        // Restore stock for every item in the order.
        for (OrderItem orderItem : orderItems) {

            MenuItem menuItem = orderItem.getMenuItem();

            menuItem.setStockQuantity(
                    menuItem.getStockQuantity() + orderItem.getQuantity()
            );

            menuRepository.save(menuItem);
        }

        order.setStatus(OrderStatus.CANCELLED);

        Order cancelledOrder = orderRepository.save(order);

        return mapToResponseDTO(cancelledOrder);
    }

    // Validates allowed order status transitions.
    private boolean isValidStatusTransition(
            OrderStatus currentStatus,
            OrderStatus newStatus) {

        return switch (currentStatus) {
            case PLACED -> newStatus == OrderStatus.PREPARING;
            case PREPARING -> newStatus == OrderStatus.OUT_FOR_DELIVERY;
            case OUT_FOR_DELIVERY -> newStatus == OrderStatus.DELIVERED;
            case DELIVERED, CANCELLED -> false;
        };
    }

    // Converts Order entity into OrderResponseDTO.
    private OrderResponseDTO mapToResponseDTO(Order order) {

        OrderResponseDTO responseDTO = new OrderResponseDTO();

        responseDTO.setId(order.getId());
        responseDTO.setOrderNumber(order.getOrderNumber());
        responseDTO.setCustomerName(order.getCustomerName());
        responseDTO.setCustomerEmail(order.getCustomerEmail());
        responseDTO.setCustomerPhone(order.getCustomerPhone());
        responseDTO.setDeliveryAddress(order.getDeliveryAddress());
        responseDTO.setStatus(order.getStatus());
        responseDTO.setTotalAmount(order.getTotalAmount());
        responseDTO.setOrderDate(order.getOrderDate());

        List<OrderItemResponseDTO> itemDTOs =
                orderItemRepository.findByOrderId(order.getId())
                        .stream()
                        .map(item -> {
                            OrderItemResponseDTO itemDTO =
                                    new OrderItemResponseDTO();

                            itemDTO.setId(item.getId());
                            itemDTO.setMenuItemId(item.getMenuItem().getId());
                            itemDTO.setMenuItemName(item.getMenuItem().getName());
                            itemDTO.setQuantity(item.getQuantity());
                            itemDTO.setPurchasePrice(item.getPurchasePrice());

                            itemDTO.setSubtotal(
                                    item.getPurchasePrice().multiply(
                                            BigDecimal.valueOf(item.getQuantity())
                                    )
                            );

                            return itemDTO;
                        })
                        .toList();

        responseDTO.setItems(itemDTOs);

        return responseDTO;
    }
}
