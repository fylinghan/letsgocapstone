package controllers;

import entities.OrderItem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.OrderItemService;

import java.util.List;

@RestController
@RequestMapping("/items")
public class OrderItemController {

    private final OrderItemService orderItemService;


    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<OrderItem>> getOrderItems(Long orderId) {
        return ResponseEntity.status(HttpStatus.OK).body(orderItemService.getOrderItemsByOrderId(orderId));
    }


}
