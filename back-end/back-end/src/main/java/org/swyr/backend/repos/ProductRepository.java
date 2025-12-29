package org.swyr.backend.repos;

import org.springframework.data.jpa.repository.Query;
import org.swyr.backend.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * Repository interface for managing {@link Product} entities.
 * <p>
 * Extends {@link JpaRepository} to provide standard CRUD operations and
 * custom query methods for accessing product data.
 * </p>
 *
 * This repository is annotated with {@link Repository}, making it a Spring-managed
 * bean that can be injected into services for database operations.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Retrieves all products of a specific type.
     *
     * @param type the product type (e.g., CARD, DECK, PACK)
     * @return a list of {@link Product} entities matching the given type
     */
    List<Product> findByProductType(Product.ProductType type);
    List<Product> findByProductTypeAndStockGreaterThanOrderByDateAddedDesc(Product.ProductType type, int stock);

    /**
     * Retrieves the five most recently added products.
     *
     * @return a list of the latest five {@link Product} entities ordered by date added
     */
    List<Product> findTop5ByOrderByDateAddedDesc();

    /**
     * Retrieves the most recently added product of a specific type.
     *
     * @param type the product type (e.g., CARD, DECK, PACK)
     * @return the latest {@link Product} entity of the given type
     */
    Product findTop1ByProductTypeOrderByDateAddedDesc(Product.ProductType type);

    /**
     * Retrieves the eight most recently added products of a specific type.
     *
     * @param type the product type (e.g., CARD, DECK, PACK)
     * @return a list of the latest eight {@link Product} entities of the given type
     */
    List<Product> findTop8ByProductTypeOrderByDateAddedDesc(Product.ProductType type);
    List<Product> findTop8ByProductTypeAndStockGreaterThanOrderByDateAddedDesc(Product.ProductType type, int stock);

    /**
     * Retrieves the three most recently added products of a specific type.
     *
     * @param type the product type (e.g., CARD, DECK, PACK)
     * @return a list of the latest three {@link Product} entities of the given type
     */
    List<Product> findTop3ByProductTypeOrderByDateAddedDesc(Product.ProductType type);

    /**
     * Retrieves all products of a specific type with a price less than the given value.
     *
     * @param type  the product type (e.g., CARD, DECK, PACK)
     * @param price the maximum price threshold
     * @return a list of {@link Product} entities matching the type and price criteria
     */
    List<Product> findByProductTypeAndPriceLessThan(Product.ProductType type, BigDecimal price);

    /**
     * Retrieves all products of a specific type, ordered by date added in descending order.
     *
     * @param type the product type (e.g., CARD, DECK, PACK)
     * @return a list of {@link Product} entities of the given type ordered by date added
     */
    List<Product> findByProductTypeOrderByDateAddedDesc(Product.ProductType type);

    /**
     * Retrieves distinct series names for products of a specific type.
     * <p>
     * Uses a custom JPQL query to group products by their series name.
     * </p>
     *
     * @param type the product type (e.g., CARD, DECK, PACK)
     * @return a list of unique series names for products of the given type
     */
    @Query("SELECT p.seriesName FROM Product p WHERE p.productType = :type GROUP BY p.seriesName")
    List<String> findSeriesNames(Product.ProductType type);

    @Modifying
    @Transactional
    @Query("UPDATE Product p SET p.stock = p.stock - :quantity WHERE p.id = :id AND p.stock >= :quantity")
    int decreaseStock(@Param("quantity") int quantity, @Param("id") Long id);
}