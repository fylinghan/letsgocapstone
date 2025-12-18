package org.swyr.backend.controllers;

import org.swyr.backend.dtos.ProductDTO;
import org.swyr.backend.entities.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.swyr.backend.services.ProductService;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * REST controller that manages product-related operations.
 * <p>
 * Provides endpoints for retrieving products (cards, decks, packs),
 * fetching homepage product selections, retrieving product details,
 * listing new products, uploading product images, and retrieving series names.
 * </p>
 *
 * Base URL: /products
 *
 * Cross-origin requests are allowed from http://localhost:5173
 */
@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {

    private final ProductService productService;

    /**
     * Constructs a new {@code ProductController} with the given service.
     *
     * @param productService the service used to handle product operations
     */
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Retrieves all card products.
     *
     * Endpoint: GET /products/cards
     *
     * @return a {@link ResponseEntity} containing a list of {@link Product}
     *         and HTTP status 200 (OK), or 500 (Internal Server Error) if retrieval fails
     */
    @GetMapping("/cards")
    public ResponseEntity<List<Product>> getCards() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(productService.getAllCards());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * Retrieves all deck products.
     *
     * Endpoint: GET /products/decks
     *
     * @return a {@link ResponseEntity} containing a list of {@link Product}
     *         and HTTP status 200 (OK), or 500 (Internal Server Error) if retrieval fails
     */
    @GetMapping("/decks")
    public ResponseEntity<List<Product>> getDecks() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(productService.getAllDecks());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * Retrieves all pack products.
     *
     * Endpoint: GET /products/packs
     *
     * @return a {@link ResponseEntity} containing a list of {@link Product}
     *         and HTTP status 200 (OK), or 500 (Internal Server Error) if retrieval fails
     */
    @GetMapping("/packs")
    public ResponseEntity<List<Product>> getPacks() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(productService.getAllPacks());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * Retrieves card products for homepage display.
     *
     * Endpoint: GET /products/homecards
     *
     * @return a {@link ResponseEntity} containing a list of {@link Product}
     *         and HTTP status 200 (OK), or 500 (Internal Server Error) if retrieval fails
     */
    @GetMapping("/homecards")
    public ResponseEntity<List<Product>> getHomeCards() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(productService.getCardsforHomepage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * Retrieves deck products for homepage display.
     *
     * Endpoint: GET /products/homedecks
     *
     * @return a {@link ResponseEntity} containing a list of {@link Product}
     *         and HTTP status 200 (OK), or 500 (Internal Server Error) if retrieval fails
     */
    @GetMapping("/homedecks")
    public ResponseEntity<List<Product>> getHomeDecks() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(productService.getDecksforHomepage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * Retrieves pack products for homepage display.
     *
     * Endpoint: GET /products/homepacks
     *
     * @return a {@link ResponseEntity} containing a list of {@link Product}
     *         and HTTP status 200 (OK), or 500 (Internal Server Error) if retrieval fails
     */
    @GetMapping("/homepacks")
    public ResponseEntity<List<Product>> getHomePacks() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(productService.getPacksforHomepage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * Retrieves a product by its ID.
     *
     * Endpoint: GET /products/{id}
     *
     * @param id the ID of the product to retrieve
     * @return a {@link ResponseEntity} containing the {@link Product}
     *         and HTTP status 200 (OK), or 404 (Not Found) if the product does not exist
     */
    @GetMapping("/{id}")
    public ResponseEntity getProductById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(productService.getProductById(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /**
     * Lists a new card product.
     *
     * Endpoint: POST /products/list/card
     *
     * @param productDTO the data transfer object containing product information
     * @return a {@link ResponseEntity} containing the created {@link Product}
     *         and HTTP status 201 (Created), or 500 (Internal Server Error) if creation fails
     */
    @PostMapping("/list/card")
    public ResponseEntity<Product> listCard(@RequestBody ProductDTO productDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(productService.listCard(productDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * Uploads an image for a card product.
     *
     * Endpoint: POST /products/list/card/upload
     *
     * @param image the image file to upload
     * @return a {@link ResponseEntity} containing the image path string and HTTP status 200 (OK),
     *         or 400 (Bad Request) if the file is invalid or upload fails
     */
    @PostMapping("/list/card/upload")
    public ResponseEntity<String> uploadCardImg(@RequestParam("file") MultipartFile image) {
        if (image.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No file uploaded");
        }

        if (image.getContentType() != null && !image.getContentType().startsWith("image/")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid image type");
        }

        try {
            String uploadDir = System.getProperty("user.dir") + "/uploads/cards/";
            String originalName = image.getOriginalFilename();
            String extension = originalName.substring(originalName.lastIndexOf("."));
            String uniqueName = UUID.randomUUID() + extension;
            File dest = new File(uploadDir + uniqueName);
            image.transferTo(dest);
            return ResponseEntity.ok("/cards/" + uniqueName);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("unable to upload");
        }
    }

    /**
     * Retrieves a list of series names for a given product type.
     *
     * Endpoint: GET /products/series?type={type}
     *
     * @param type the product type (e.g., "CARD", "DECK", "PACK")
     * @return a {@link ResponseEntity} containing a list of series names and HTTP status 200 (OK)
     */
    @GetMapping("/series")
    public ResponseEntity<List<String>> getSeries(@RequestParam String type) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getSeriesList(type));
    }
}