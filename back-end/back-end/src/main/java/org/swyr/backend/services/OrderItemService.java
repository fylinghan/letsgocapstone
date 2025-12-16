package org.swyr.backend.services;

import org.swyr.backend.dtos.OrderItemDTO;
import org.swyr.backend.entities.Order;
import org.swyr.backend.entities.OrderItem;
import org.springframework.stereotype.Service;
import org.swyr.backend.repos.OrderItemRepository;
import org.swyr.backend.repos.ProductRepository;

import java.util.List;

@Service
public class OrderItemService {

    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductService productService;

    public OrderItemService(ProductRepository productRepository, OrderItemRepository orderItemRepository, ProductService productService) {
        this.productRepository = productRepository;
        this.orderItemRepository = orderItemRepository;
        this.productService = productService;
    }

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

    public List<OrderItem> getOrderItemsByOrderId(Long orderId) {
        return orderItemRepository.getOrderItemsByOrderOrderId(orderId);
    }
}
