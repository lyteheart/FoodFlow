package foodordering.order.service;

import foodordering.order.dto.OrderRequestDTO;
import foodordering.order.dto.OrderResponseDTO;
import foodordering.order.enums.OrderStatus;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;import java.util.List;

public interface OrderService {

    // Places an order after validating items and available stock.
    OrderResponseDTO placeOrder(OrderRequestDTO requestDTO);

    // Retrieves an order using its ID.
    OrderResponseDTO getOrderById(Long id);

    // Retrieves all orders.
    Page<OrderResponseDTO> getAllOrders(Pageable pageable);

    // Updates the status of an existing order.
    OrderResponseDTO updateOrderStatus(Long id, OrderStatus status);

    // Cancels an existing order.
    OrderResponseDTO cancelOrder(Long id);
}