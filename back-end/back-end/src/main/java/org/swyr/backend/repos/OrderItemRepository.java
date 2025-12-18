package org.swyr.backend.repos;

import org.swyr.backend.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing {@link OrderItem} entities.
 * <p>
 * Extends {@link JpaRepository} to provide standard CRUD operations and
 * custom query methods for accessing order item data.
 * </p>
 *
 * This repository is annotated with {@link Repository}, making it a Spring-managed
 * bean that can be injected into services for database operations.
 */
@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    /**
     * Retrieves all {@link OrderItem} entities associated with a specific order ID.
     * <p>
     * Spring Data JPA automatically generates the query based on the method name,
     * which navigates the relationship between {@link OrderItem} and {@link org.swyr.backend.entities.Order}.
     * </p>
     *
     * @param orderId the unique identifier of the order
     * @return a list of {@link OrderItem} entities belonging to the specified order
     */
    List<OrderItem> getOrderItemsByOrderOrderId(Long orderId);
}