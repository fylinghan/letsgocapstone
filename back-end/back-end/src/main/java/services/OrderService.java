package services;

import dtos.OrderDTO;
import entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repos.OrderItemRepository;
import repos.OrderRepository;
import repos.ProductRepository;
import repos.UserRepository;

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
