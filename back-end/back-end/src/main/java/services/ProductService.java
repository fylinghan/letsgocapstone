package services;

import dtos.ProductDTO;
import entities.Product;
import org.springframework.stereotype.Service;
import repos.ProductRepository;
import repos.UserRepository;

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

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> getAllCards() {
        return productRepository.findByProductType(Product.ProductType.CARD);
    }

    public List<Product> getAllPacks() {
        return productRepository.findByProductType(Product.ProductType.PACK);
    }

    public List<Product> getLatest() {
        return productRepository.findTop5ByOrderByDateaddedDesc();
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
        return productRepository.findTop1ByProductTypeOrderByDateaddedDesc(Product.ProductType.PACK);
    }

    public List<Product> getCardsforHomepage() {
        return productRepository.findTop8ByProductTypeOrderByDateaddedDesc(Product.ProductType.CARD);
    }

    public List<Product> getDecksforHomepage() {
        return productRepository.findTop8ByProductTypeOrderByDateaddedDesc(Product.ProductType.DECK);
    }

    public List<Product> getPacksforHomepage() {
        return productRepository.findTop3ByProductTypeOrderByDateaddedDesc(Product.ProductType.PACK);
    }

}
