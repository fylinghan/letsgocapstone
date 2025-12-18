package org.swyr.backend.controllers;

import org.swyr.backend.dtos.OrderDTO;
import org.swyr.backend.entities.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.swyr.backend.services.OrderService;

import java.util.List;

/**
 * REST controller that handles operations related to orders.
 * Provides endpoints for retrieving user orders, fetching order details,
 * and submitting new orders.
 *
 * Base URL: /order
 *
 * Cross-origin requests are allowed from http://localhost:5173
 */
@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "http://localhost:5173")
public class OrderController {

    private final OrderService orderService;

    /**
     * Constructs a new OrderController with the given OrderService.
     *
     * @param orderService the service used to handle order-related operations
     */
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Retrieves all orders associated with a given user email.
     *
     * Endpoint: GET /order/user?email={email}
     *
     * @param email the email address of the user whose orders are to be retrieved
     * @return ResponseEntity containing a list of orders and HTTP status 200 (OK)
     */
    @GetMapping("/user")
    public ResponseEntity<List<Order>> getOrders(@RequestParam String email) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getUserOrders(email));
    }

    /**
     * Retrieves the details of a specific order by its ID.
     *
     * Endpoint: GET /order/details?orderId={id}
     *
     * @param orderId the ID of the order to retrieve
     * @return ResponseEntity containing the order details and HTTP status 200 (OK)
     */
    @GetMapping("/details")
    public ResponseEntity<Order> getOrderDetails(@RequestParam Long orderId) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrder(orderId));
    }

    /**
     * Submits a new order based on the provided OrderDTO.
     *
     * Endpoint: POST /order/submit
     *
     * @param orderDTO the data transfer object containing order information
     * @return ResponseEntity containing the created order and HTTP status 201 (Created),
     *         or HTTP status 400 (Bad Request) if the order data is invalid
     */
    @PostMapping("/submit")
    public ResponseEntity submitOrder(@RequestBody OrderDTO orderDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(orderService.generateOrder(orderDTO));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}