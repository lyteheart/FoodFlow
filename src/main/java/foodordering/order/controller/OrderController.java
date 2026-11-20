package foodordering.order.controller;

import foodordering.order.dto.OrderRequestDTO;
import foodordering.order.dto.OrderResponseDTO;
import foodordering.order.enums.OrderStatus;
import foodordering.order.service.OrderService;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    // Constructor injection of OrderService
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Place a new order
    @PostMapping
    public ResponseEntity<OrderResponseDTO> placeOrder(
            @Valid @RequestBody OrderRequestDTO requestDTO) {

        OrderResponseDTO response = orderService.placeOrder(requestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Retrieve all orders with pagination and sorting
    @GetMapping
    public ResponseEntity<Page<OrderResponseDTO>> getAllOrders(
            @PageableDefault(size = 10, sort = "orderDate", direction = Sort.Direction.DESC)
            Pageable pageable) {

        Page<OrderResponseDTO> orders = orderService.getAllOrders(pageable);

        return ResponseEntity.ok(orders);
    }

    // Retrieve an order by its ID
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> getOrderById(
            @PathVariable Long id) {

        OrderResponseDTO response = orderService.getOrderById(id);

        return ResponseEntity.ok(response);
    }

    // Update the status of an existing order
    @PatchMapping("/{id}/status")
    public ResponseEntity<OrderResponseDTO> updateOrderStatus(
            @PathVariable Long id,
            @RequestParam OrderStatus status) {

        OrderResponseDTO response =
                orderService.updateOrderStatus(id, status);

        return ResponseEntity.ok(response);
    }

    // Cancel an existing order
    @PatchMapping("/{id}/cancel")
    public ResponseEntity<OrderResponseDTO> cancelOrder(
            @PathVariable Long id) {

        OrderResponseDTO response = orderService.cancelOrder(id);

        return ResponseEntity.ok(response);
    }
}