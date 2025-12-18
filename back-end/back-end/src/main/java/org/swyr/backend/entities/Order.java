package org.swyr.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

/**
 * Entity representing an order in the system.
 * <p>
 * This class maps to the {@code orders} table in the database and contains
 * information about the order such as its ID, shipping status, order date,
 * shipping address, associated user, and the list of ordered items.
 * </p>
 *
 * Relationships:
 * <ul>
 *   <li>Many-to-One with {@link User} (each order belongs to a single user).</li>
 *   <li>One-to-Many with {@link OrderItem} (an order can contain multiple items).</li>
 * </ul>
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {

    /**
     * The unique identifier for the order.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderid")
    private Long orderId;

    /**
     * The current shipping status of the order.
     * <p>
     * Possible values are defined in the {@link status} enum.
     * </p>
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "shippingstatus", nullable = false)
    private status shippingStatus;

    /**
     * The date when the order was placed.
     */
    @Column(name = "orderdate", nullable = false)
    private LocalDate orderDate;

    /**
     * The shipping address where the order should be delivered.
     */
    @Column(name = "shippingaddress", nullable = false)
    private String shippingAddress;

    /**
     * The user who placed the order.
     * <p>
     * This field is ignored during JSON serialization to prevent circular references.
     * </p>
     */
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "useremail")
    private User user;

    /**
     * The list of items included in the order.
     * <p>
     * Managed reference is used to handle bidirectional JSON serialization with {@link OrderItem}.
     * </p>
     */
    @JsonManagedReference
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderedItems;

    /**
     * Retrieves the email of the user who placed the order.
     * <p>
     * This method is marked as {@code @Transient} because it is not persisted in the database.
     * </p>
     *
     * @return the email of the user associated with the order
     */
    @Transient
    public String getUserEmail() {
        return user.getEmail();
    }

    /**
     * Enumeration representing the possible shipping statuses of an order.
     */
    public enum status {
        /** The order has been placed but not yet shipped. */
        ORDERED,

        /** The order has been shipped but not yet delivered. */
        SHIPPED,

        /** The order has been delivered to the customer. */
        DELIVERED
    }
}