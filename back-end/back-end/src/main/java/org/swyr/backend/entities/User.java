package org.swyr.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Entity representing a user in the system.
 * <p>
 * This class maps to the {@code userstable} table in the database and contains
 * information about the user such as their email, password, and associations
 * with orders and products.
 * </p>
 *
 * Relationships:
 * <ul>
 *   <li>One-to-Many with {@link Order} (a user can place multiple orders).</li>
 *   <li>One-to-Many with {@link Product} (a user can list multiple products).</li>
 * </ul>
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "userstable")
public class User {

    /**
     * The unique email address of the user.
     * <p>
     * This field serves as the primary key and cannot be updated once set.
     * </p>
     */
    @Id
    @Column(name = "email", unique = true, nullable = false, updatable = false)
    private String email;

    /**
     * The password associated with the user account.
     */
    @Column(name = "pw")
    private String password;

    /**
     * The list of orders placed by the user.
     */
    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    /**
     * The list of products (e.g., cards) listed or owned by the user.
     */
    @OneToMany(mappedBy = "user")
    private List<Product> cards;
}