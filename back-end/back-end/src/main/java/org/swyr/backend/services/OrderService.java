package org.swyr.backend.services;

import org.swyr.backend.dtos.OrderDTO;
import org.swyr.backend.entities.Order;
import org.springframework.stereotype.Service;
import org.swyr.backend.repos.OrderRepository;
import org.swyr.backend.repos.UserRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderItemService orderItemService;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository, OrderItemService orderItemService) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.orderItemService = orderItemService;
    }


    public List<Order> getUserOrders(String email) {
        return orderRepository.findByUser_Email(email);
    }

    public Long generateOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setOrderDate(LocalDate.now());
        order.setUser(userRepository.findById(orderDTO.getEmail()).orElse(null));
        order.setShippingAddress(orderDTO.getShippingAddress());
        order.setShippingStatus(Order.status.ORDERED);
        orderRepository.save(order);
        orderItemService.storeOrderItem(orderDTO.getOrderedItems(), order);
        return order.getOrderId();
    }

    public Order getOrder(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

}
