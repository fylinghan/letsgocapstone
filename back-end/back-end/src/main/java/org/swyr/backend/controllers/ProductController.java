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
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/cards")
    public ResponseEntity<List<Product>> getCards() {

        try {
            return ResponseEntity.status(HttpStatus.OK).body(productService.getAllCards());
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/decks")
    public ResponseEntity<List<Product>> getDecks() {

        try {
            return ResponseEntity.status(HttpStatus.OK).body(productService.getAllDecks());
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/packs")
    public ResponseEntity<List<Product>> getPacks() {

        try {
            return ResponseEntity.status(HttpStatus.OK).body(productService.getAllPacks());
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/homecards")
    public ResponseEntity<List<Product>> getHomeCards() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(productService.getCardsforHomepage());
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/homedecks")
    public ResponseEntity<List<Product>> getHomeDecks() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(productService.getDecksforHomepage());
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/homepacks")
    public ResponseEntity<List<Product>> getHomePacks() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(productService.getPacksforHomepage());
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getProductById(@PathVariable Long id) {

        try {
            return ResponseEntity.status(HttpStatus.OK).body(productService.getProductById(id));
        }
        catch(IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/list/card")
    public ResponseEntity<Product> listCard(@RequestBody ProductDTO productDTO) {

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(productService.listCard(productDTO));
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }

    @PostMapping("/list/card/upload")
    public ResponseEntity<String> uploadCardImg(@RequestParam("file") MultipartFile image) {
        if (image.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No file uploaded");
        }


        if (image.getContentType() != null && !image.getContentType().startsWith("image/")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid image type");
        }

        try{
            String uploadDir = System.getProperty("user.dir") + "/uploads/cards/";
            String originalName = image.getOriginalFilename();
            String extension = originalName.substring(originalName.lastIndexOf("."));
            String uniqueName = UUID.randomUUID() + extension;
            File dest = new File(uploadDir + uniqueName);
            image.transferTo(dest);
            return ResponseEntity.ok("http://localhost:8080/cards/" + uniqueName);

        } catch (IOException e) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("unable to upload");
        }

    }

    @GetMapping("/series")
    public ResponseEntity<List<String>> getSeries(@RequestParam String type) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getSeriesList(type));
    }

}
