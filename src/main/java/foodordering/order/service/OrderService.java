package foodordering.order.service;

import foodordering.order.dto.OrderRequestDTO;
import foodordering.order.dto.OrderResponseDTO;
import foodordering.order.enums.OrderStatus;

import java.util.List;

public interface OrderService {

    // Places an order after validating items and available stock.
    OrderResponseDTO placeOrder(OrderRequestDTO requestDTO);

    // Retrieves an order using its ID.
    OrderResponseDTO getOrderById(Long id);

    // Retrieves all orders.
    List<OrderResponseDTO> getAllOrders();

    // Updates the status of an existing order.
    OrderResponseDTO updateOrderStatus(Long id, OrderStatus status);

    // Cancels an existing order.
    OrderResponseDTO cancelOrder(Long id);
}