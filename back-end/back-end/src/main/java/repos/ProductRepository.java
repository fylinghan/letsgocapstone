package repos;

import entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByProductType(Product.ProductType type);
    List<Product> findTop5ByOrderByDateaddedDesc();
    Product findTop1ByProductTypeOrderByDateaddedDesc(Product.ProductType type);
    List<Product> findTop8ByProductTypeOrderByDateaddedDesc(Product.ProductType type);
    List<Product> findTop3ByProductTypeOrderByDateaddedDesc(Product.ProductType type);
    List<Product> findByProductTypeAndPriceLessThan(Product.ProductType type, BigDecimal price);
    List<Product> findByProductTypeOrderByDateaddedDesc(Product.ProductType type);
}
