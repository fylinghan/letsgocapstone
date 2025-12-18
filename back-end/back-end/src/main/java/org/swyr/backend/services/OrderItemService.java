package org.swyr.backend.services;

import org.swyr.backend.dtos.OrderItemDTO;
import org.swyr.backend.entities.Order;
import org.swyr.backend.entities.OrderItem;
import org.springframework.stereotype.Service;
import org.swyr.backend.repos.OrderItemRepository;
import org.swyr.backend.repos.ProductRepository;

import java.util.List;

/**
 * Service class that manages operations related to {@link OrderItem}.
 * <p>
 * Provides functionality for storing order items associated with an order
 * and retrieving order items by order ID.
 * </p>
 *
 * Dependencies:
 * <ul>
 *   <li>{@link ProductRepository} - for retrieving product information.</li>
 *   <li>{@link OrderItemRepository} - for persisting and querying order items.</li>
 *   <li>{@link ProductService} - for updating product stock after an order is placed.</li>
 * </ul>
 */
@Service
public class OrderItemService {

    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductService productService;

    /**
     * Constructs a new {@code OrderItemService} with the required repositories and services.
     *
     * @param productRepository     repository for accessing product data
     * @param orderItemRepository   repository for accessing order item data
     * @param productService        service for managing product-related operations
     */
    public OrderItemService(ProductRepository productRepository,
                            OrderItemRepository orderItemRepository,
                            ProductService productService) {
        this.productRepository = productRepository;
        this.orderItemRepository = orderItemRepository;
        this.productService = productService;
    }

    /**
     * Stores a list of order items for a given order.
     * <p>
     * For each {@link OrderItemDTO}, this method:
     * <ul>
     *   <li>Creates a new {@link OrderItem} entity.</li>
     *   <li>Associates it with the given {@link Order}.</li>
     *   <li>Retrieves the corresponding product from the {@link ProductRepository}.</li>
     *   <li>Sets the quantity of the product ordered.</li>
     *   <li>Saves the order item to the database.</li>
     *   <li>Updates the product stock using {@link ProductService#stockChange(int, Long)}.</li>
     * </ul>
     * </p>
     *
     * @param orderItemsDTO the list of order item DTOs containing product IDs and quantities
     * @param order         the order to which the items belong
     */
    public void storeOrderItem(List<OrderItemDTO> orderItemsDTO, Order order) {
        for (OrderItemDTO orderItemDTO : orderItemsDTO) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(productRepository.findById(orderItemDTO.getProductId()).orElse(null));
            orderItem.setQuantity(orderItemDTO.getQuantity());
            orderItemRepository.save(orderItem);
            productService.stockChange(orderItemDTO.getQuantity(), orderItemDTO.getProductId());
        }
    }

    /**
     * Retrieves all order items associated with a specific order ID.
     *
     * @param orderId the unique identifier of the order
     * @return a list of {@link OrderItem} entities belonging to the specified order
     */
    public List<OrderItem> getOrderItemsByOrderId(Long orderId) {
        return orderItemRepository.getOrderItemsByOrderOrderId(orderId);
    }
}