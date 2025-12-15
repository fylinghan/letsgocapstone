package org.swyr.backend.controllers;

import org.swyr.backend.dtos.OrderDTO;
import org.swyr.backend.entities.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.swyr.backend.services.OrderService;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/user")
    public ResponseEntity<List<Order>> getOrders(@RequestParam String email) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getUserOrders(email));
    }

    @GetMapping("/details")
    public ResponseEntity<Order> getOrderDetails(@RequestParam Long orderId) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrder(orderId));
    }

    @PostMapping("/submit")
    public ResponseEntity submitOrder(@RequestBody OrderDTO orderDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.generateOrder(orderDTO));
    }

}
