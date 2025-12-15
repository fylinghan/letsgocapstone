package org.swyr.backend.repos;

import org.swyr.backend.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByProductType(Product.ProductType type);
    List<Product> findTop5ByOrderByDateAddedDesc();
    Product findTop1ByProductTypeOrderByDateAddedDesc(Product.ProductType type);
    List<Product> findTop8ByProductTypeOrderByDateAddedDesc(Product.ProductType type);
    List<Product> findTop3ByProductTypeOrderByDateAddedDesc(Product.ProductType type);
    List<Product> findByProductTypeAndPriceLessThan(Product.ProductType type, BigDecimal price);
    List<Product> findByProductTypeOrderByDateAddedDesc(Product.ProductType type);
}
