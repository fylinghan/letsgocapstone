package org.swyr.backend.services;

import org.springframework.transaction.annotation.Transactional;
import org.swyr.backend.dtos.OrderDTO;
import org.swyr.backend.entities.Order;
import org.springframework.stereotype.Service;
import org.swyr.backend.repos.OrderRepository;
import org.swyr.backend.repos.UserRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * Service class that manages operations related to {@link Order}.
 * <p>
 * Provides functionality for retrieving user orders, generating new orders,
 * and fetching order details by ID.
 * </p>
 *
 * Dependencies:
 * <ul>
 *   <li>{@link OrderRepository} - for persisting and querying orders.</li>
 *   <li>{@link UserRepository} - for retrieving user information.</li>
 *   <li>{@link OrderItemService} - for storing order items associated with an order.</li>
 * </ul>
 */
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderItemService orderItemService;

    /**
     * Constructs a new {@code OrderService} with the required repositories and services.
     *
     * @param orderRepository   repository for accessing order data
     * @param userRepository    repository for accessing user data
     * @param orderItemService  service for managing order item operations
     */
    public OrderService(OrderRepository orderRepository,
                        UserRepository userRepository,
                        OrderItemService orderItemService) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.orderItemService = orderItemService;
    }

    /**
     * Retrieves all orders associated with a specific user email.
     *
     * @param email the email address of the user whose orders are to be retrieved
     * @return a list of {@link Order} entities belonging to the specified user
     */
    public List<Order> getUserOrders(String email) {
        return orderRepository.findByUser_Email(email);
    }

    /**
     * Generates a new order based on the provided {@link OrderDTO}.
     * <p>
     * This method:
     * <ul>
     *   <li>Validates that required fields are not null or empty.</li>
     *   <li>Creates a new {@link Order} entity with the current date, user, shipping address, and status.</li>
     *   <li>Saves the order to the database.</li>
     *   <li>Delegates to {@link OrderItemService} to store associated order items.</li>
     * </ul>
     * </p>
     *
     * <p>
     * The method is annotated with {@link Transactional} to ensure atomicity,
     * rolling back in case of a {@link NullPointerException}.
     * </p>
     *
     * @param orderDTO the data transfer object containing order details
     * @return the unique identifier of the newly created order
     * @throws IllegalArgumentException if required fields are null or empty
     */
    @Transactional(rollbackFor = NullPointerException.class)
    public Long generateOrder(OrderDTO orderDTO) {
        if (orderDTO.getOrderedItems() == null || orderDTO.getOrderedItems().isEmpty()
                || orderDTO.getEmail() == null || orderDTO.getEmail().isEmpty()
                || orderDTO.getShippingAddress() == null || orderDTO.getShippingAddress().isEmpty()) {
            throw new IllegalArgumentException("Fields cannot be empty or null");
        }

        Order order = new Order();
        order.setOrderDate(LocalDate.now());
        order.setUser(userRepository.findById(orderDTO.getEmail()).orElse(null));
        order.setShippingAddress(orderDTO.getShippingAddress());
        order.setShippingStatus(Order.status.ORDERED);

        orderRepository.save(order);
        orderItemService.storeOrderItem(orderDTO.getOrderedItems(), order);

        return order.getOrderId();
    }

    /**
     * Retrieves an order by its unique identifier.
     *
     * @param orderId the unique identifier of the order
     * @return the {@link Order} entity if found, or {@code null} if not present
     */
    public Order getOrder(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }
}