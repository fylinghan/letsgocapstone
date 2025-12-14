package services;

import dtos.OrderItemDTO;
import entities.Order;
import entities.OrderItem;
import org.springframework.stereotype.Service;
import repos.OrderItemRepository;
import repos.OrderRepository;
import repos.ProductRepository;

import java.util.List;

@Service
public class OrderItemService {

    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderItemService(ProductRepository productRepository, OrderItemRepository orderItemRepository) {
        this.productRepository = productRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public void storeOrderItem(List<OrderItemDTO> orderItemsDTO, Order order) {
        for (OrderItemDTO orderItemDTO : orderItemsDTO) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(productRepository.findById(orderItemDTO.getProduct_id()).orElse(null));
            orderItem.setQuantity(orderItemDTO.getQuantity());
            orderItemRepository.save(orderItem);
        }
    }

    public List<OrderItem> getOrderItemsByOrderId(Long orderId) {
        return orderItemRepository.getOrderItemsByOrderId(orderId);
    }
}
