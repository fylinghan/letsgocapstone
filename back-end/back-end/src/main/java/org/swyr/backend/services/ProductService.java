package org.swyr.backend.services;

import org.swyr.backend.dtos.ProductDTO;
import org.swyr.backend.entities.Product;
import org.springframework.stereotype.Service;
import org.swyr.backend.repos.ProductRepository;
import org.swyr.backend.repos.UserRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * Service class that manages operations related to {@link Product}.
 * <p>
 * Provides functionality for retrieving products by type, fetching product details,
 * listing new products, updating stock, and retrieving series names.
 * </p>
 *
 * Dependencies:
 * <ul>
 *   <li>{@link ProductRepository} - for persisting and querying product data.</li>
 *   <li>{@link UserRepository} - for retrieving user information associated with products.</li>
 * </ul>
 */
@Service
public class ProductService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    /**
     * Constructs a new {@code ProductService} with the required repositories.
     *
     * @param productRepository repository for accessing product data
     * @param userRepository    repository for accessing user data
     */
    public ProductService(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    /**
     * Retrieves all products of type {@code DECK}.
     *
     * @return a list of {@link Product} entities of type DECK
     */
    public List<Product> getAllDecks() {
        return productRepository.findByProductType(Product.ProductType.DECK);
    }

    /**
     * Retrieves a product by its unique identifier.
     *
     * @param id the unique identifier of the product
     * @return the {@link Product} entity if found
     * @throws IllegalArgumentException if the product does not exist
     */
    public Product getProductById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) {
            throw new IllegalArgumentException("Product does not exist");
        }
        return product;
    }

    /**
     * Retrieves all products of type {@code CARD}.
     *
     * @return a list of {@link Product} entities of type CARD
     */
    public List<Product> getAllCards() {
        return productRepository.findByProductTypeAndStockGreaterThanOrderByDateAddedDesc(Product.ProductType.CARD, 0);
    }

    /**
     * Retrieves all products of type {@code PACK}.
     *
     * @return a list of {@link Product} entities of type PACK
     */
    public List<Product> getAllPacks() {
        return productRepository.findByProductType(Product.ProductType.PACK);
    }

    /**
     * Retrieves the five most recently added products.
     *
     * @return a list of the latest five {@link Product} entities
     */
    public List<Product> getLatest() {
        return productRepository.findTop5ByOrderByDateAddedDesc();
    }

    /**
     * Creates and lists a new card product based on the provided {@link ProductDTO}.
     *
     * @param productDTO the data transfer object containing product details
     * @return the newly created {@link Product} entity
     */
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

    /**
     * Updates the stock of a product by subtracting the given quantity.
     *
     * @param quantity the quantity to subtract from the product's stock
     * @param id       the unique identifier of the product
     */
    public void stockChange(int quantity, Long id) {
        Product product = productRepository.findById(id).get();
        product.setStock(product.getStock() - quantity);
        productRepository.save(product);
    }

    /**
     * Retrieves the most recently added product of type {@code PACK}.
     *
     * @return the latest {@link Product} entity of type PACK
     */
    public Product getLatestPack() {
        return productRepository.findTop1ByProductTypeOrderByDateAddedDesc(Product.ProductType.PACK);
    }

    /**
     * Retrieves the eight most recently added products of type {@code CARD} for homepage display.
     *
     * @return a list of the latest eight {@link Product} entities of type CARD
     */
    public List<Product> getCardsforHomepage() {
        return productRepository.findTop8ByProductTypeAndStockGreaterThanOrderByDateAddedDesc(Product.ProductType.CARD, 0);
    }

    /**
     * Retrieves the eight most recently added products of type {@code DECK} for homepage display.
     *
     * @return a list of the latest eight {@link Product} entities of type DECK
     */
    public List<Product> getDecksforHomepage() {
        return productRepository.findTop8ByProductTypeOrderByDateAddedDesc(Product.ProductType.DECK);
    }

    /**
     * Retrieves the three most recently added products of type {@code PACK} for homepage display.
     *
     * @return a list of the latest three {@link Product} entities of type PACK
     */
    public List<Product> getPacksforHomepage() {
        return productRepository.findTop3ByProductTypeOrderByDateAddedDesc(Product.ProductType.PACK);
    }

    /**
     * Retrieves distinct series names for products of a given type.
     *
     * @param type the product type (e.g., "CARD", "DECK", "PACK")
     * @return a list of unique series names for products of the given type
     * @throws IllegalArgumentException if the provided type is invalid
     */
    public List<String> getSeriesList(String type) {
        switch (type.toUpperCase()) {
            case "DECK":
                return productRepository.findSeriesNames(Product.ProductType.DECK);
            case "CARD":
                return productRepository.findSeriesNames(Product.ProductType.CARD);
            case "PACK":
                return productRepository.findSeriesNames(Product.ProductType.PACK);
        }
        throw new IllegalArgumentException("Invalid type");
    }
}