package org.swyr.backend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity representing an item within an order.
 * <p>
 * This class maps to the {@code orderitems} table in the database and contains
 * information about a specific product included in an order, such as its quantity
 * and references to the associated {@link Order} and {@link Product}.
 * </p>
 *
 * Relationships:
 * <ul>
 *   <li>Many-to-One with {@link Order} (each order item belongs to a single order).</li>
 *   <li>Many-to-One with {@link Product} (each order item references a single product).</li>
 * </ul>
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orderitems")
public class OrderItem {

    /**
     * The unique identifier for the order item.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderitemid")
    private Long orderItemId;

    /**
     * The order to which this item belongs.
     * <p>
     * Marked with {@link JsonBackReference} to prevent circular references
     * during JSON serialization with {@link Order}.
     * </p>
     */
    @ManyToOne
    @JoinColumn(name = "orderid", nullable = false, updatable = false)
    @JsonBackReference
    private Order order;

    /**
     * The product associated with this order item.
     */
    @ManyToOne
    @JoinColumn(name = "productid", nullable = false, updatable = false)
    private Product product;

    /**
     * The quantity of the product included in the order.
     */
    @Column(name = "quantity", nullable = false)
    private int quantity;
}