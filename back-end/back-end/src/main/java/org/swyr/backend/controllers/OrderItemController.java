package org.swyr.backend.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.swyr.backend.entities.OrderItem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.swyr.backend.services.OrderItemService;

import java.util.List;

/**
 * REST controller that manages order item operations.
 * <p>
 * Provides endpoints for retrieving items associated with a specific order.
 * </p>
 *
 * Base URL: /order/items
 *
 * Cross-origin requests are allowed from http://localhost:5173
 */
@RestController
@RequestMapping("/order/items")
@CrossOrigin(origins = "http://localhost:5173")
public class OrderItemController {

    private final OrderItemService orderItemService;

    /**
     * Constructs a new {@code OrderItemController} with the given service.
     *
     * @param orderItemService the service used to handle order item operations
     */
    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    /**
     * Retrieves all order items belonging to a specific order.
     *
     * <p>Endpoint: GET /order/items/list?orderId={id}</p>
     *
     * @param orderId the ID of the order whose items are to be retrieved
     * @return a {@link ResponseEntity} containing a list of {@link OrderItem}
     *         and HTTP status 200 (OK)
     */
    @GetMapping("/list")
    public ResponseEntity<List<OrderItem>> getOrderItems(Long orderId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(orderItemService.getOrderItemsByOrderId(orderId));
    }
}