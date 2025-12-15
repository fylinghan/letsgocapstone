package org.swyr.backend.repos;

import org.swyr.backend.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    public List<OrderItem> getOrderItemsByOrderOrderId(Long orderId);

}
