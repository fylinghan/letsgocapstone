package org.swyr.backend.services;

import org.swyr.backend.dtos.ProductDTO;
import org.swyr.backend.entities.Product;
import org.springframework.stereotype.Service;
import org.swyr.backend.repos.ProductRepository;
import org.swyr.backend.repos.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public List<Product> getAllDecks() {
        return productRepository.findByProductType(Product.ProductType.DECK);
    }

    public Product getProductById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) {
            throw new IllegalArgumentException("Product does not exist");
        }
        return product;
    }

    public List<Product> getAllCards() {
        return productRepository.findByProductType(Product.ProductType.CARD);
    }

    public List<Product> getAllPacks() {
        return productRepository.findByProductType(Product.ProductType.PACK);
    }

    public List<Product> getLatest() {
        return productRepository.findTop5ByOrderByDateAddedDesc();
    }

    public Product listCard(ProductDTO productDTO) {
        Product product = new Product();
        product.setProductType(Product.ProductType.CARD);
        product.setProductName(productDTO.getProductName());
        product.setPrice(productDTO.getPrice());
        product.setImgPath(productDTO.getImgPath());
        product.setUser(userRepository.findById(productDTO.getUserEmail()).orElse(null));
        product.setDateAdded(LocalDate.now());
        product.setStock(1);
        product.setSeriesName(productDTO.getSeriesName());
        productRepository.save(product);
        return product;
    }

    public void stockChange(int quantity, Long id) {
        Product product = productRepository.findById(id).get();
        product.setStock(product.getStock() - quantity);
        productRepository.save(product);
    }

    public Product getLatestPack() {
        return productRepository.findTop1ByProductTypeOrderByDateAddedDesc(Product.ProductType.PACK);
    }

    public List<Product> getCardsforHomepage() {
        return productRepository.findTop8ByProductTypeOrderByDateAddedDesc(Product.ProductType.CARD);
    }

    public List<Product> getDecksforHomepage() {
        return productRepository.findTop8ByProductTypeOrderByDateAddedDesc(Product.ProductType.DECK);
    }

    public List<Product> getPacksforHomepage() {
        return productRepository.findTop3ByProductTypeOrderByDateAddedDesc(Product.ProductType.PACK);
    }

}
