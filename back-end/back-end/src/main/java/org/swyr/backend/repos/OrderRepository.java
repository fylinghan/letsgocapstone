package org.swyr.backend.repos;

import org.swyr.backend.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing {@link Order} entities.
 * <p>
 * Extends {@link JpaRepository} to provide standard CRUD operations and
 * custom query methods for accessing order data.
 * </p>
 *
 * This repository is annotated with {@link Repository}, making it a Spring-managed
 * bean that can be injected into services for database operations.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    /**
     * Retrieves all {@link Order} entities associated with a specific user email.
     * <p>
     * Spring Data JPA automatically generates the query based on the method name,
     * which navigates the relationship between {@link Order} and {@link org.swyr.backend.entities.User}.
     * </p>
     *
     * @param email the email address of the user whose orders are to be retrieved
     * @return a list of {@link Order} entities belonging to the specified user
     */
    List<Order> findByUser_Email(String email);
}