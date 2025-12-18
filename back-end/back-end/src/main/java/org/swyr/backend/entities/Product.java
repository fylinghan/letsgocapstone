package org.swyr.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Entity representing a product in the system.
 * <p>
 * This class maps to the {@code products} table in the database and contains
 * information about a product such as its ID, stock, price, name, series,
 * date added, type, image path, and associations with users and order items.
 * </p>
 *
 * Relationships:
 * <ul>
 *   <li>Many-to-One with {@link User} (each product is listed/owned by a single user).</li>
 *   <li>One-to-Many with {@link OrderItem} (a product can appear in multiple order items).</li>
 * </ul>
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class Product {

    /**
     * The unique identifier for the product.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productid")
    private Long productID;

    /**
     * The available stock quantity of the product.
     */
    @Column(name = "stock", nullable = false)
    private int stock;

    /**
     * The price of the product.
     */
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    /**
     * The name of the product.
     */
    @Column(name = "productname", nullable = false)
    private String productName;

    /**
     * The name of the series to which the product belongs.
     */
    @Column(name = "seriesname", nullable = false)
    private String seriesName;

    /**
     * The date when the product was added to the system.
     * <p>
     * This field is not updatable once set.
     * </p>
     */
    @Column(name = "dateadded", nullable = false, updatable = false)
    private LocalDate dateAdded;

    /**
     * The type of the product (e.g., CARD, DECK, PACK).
     * <p>
     * Defined by the {@link ProductType} enum.
     * </p>
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "producttype", nullable = false)
    private ProductType productType;

    /**
     * The relative or absolute path to the product's image.
     */
    @Column(name = "imgpath")
    private String imgPath;

    /**
     * The user who listed or owns the product.
     * <p>
     * This field is ignored during JSON serialization to prevent circular references.
     * </p>
     */
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "useremail")
    private User user;

    /**
     * Retrieves the email of the user who owns the product.
     * <p>
     * This method is marked as {@code @Transient} because it is not persisted in the database.
     * </p>
     *
     * @return the email of the user associated with the product
     */
    @Transient
    public String getUserEmail() {
        return user.getEmail();
    }

    /**
     * The list of order items that reference this product.
     * <p>
     * This field is ignored during JSON serialization to prevent circular references.
     * </p>
     */
    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<OrderItem> orderItem;

    /**
     * Enumeration representing the possible types of products.
     */
    public enum ProductType {
        /** A single card product. */
        CARD,

        /** A deck product containing multiple cards. */
        DECK,

        /** A pack product containing a set of cards. */
        PACK
    }
}