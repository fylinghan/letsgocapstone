package controllers;

import dtos.ProductDTO;
import entities.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import services.ProductService;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
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

    @PostMapping("/list/card")
    public ResponseEntity<Product> listCard(@RequestParam ProductDTO productDTO) {

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(productService.listCard(productDTO));
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }

    @PostMapping("/list/card/upload")
    public ResponseEntity<String> uploadCardImg(@RequestParam MultipartFile image) {
        if (image.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No file uploaded");
        }


        if (image.getContentType() != null && !image.getContentType().startsWith("image/")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid image type");
        }

        try{
            String uploadDir = "uploads/";
            String originalName = image.getOriginalFilename();
            String extension = originalName.substring(originalName.lastIndexOf("."));
            String uniqueName = UUID.randomUUID().toString() + extension;
            File dest = new File(uploadDir + uniqueName);
            image.transferTo(dest);
            return ResponseEntity.ok("http://localhost:8080/" + uniqueName);

        } catch (IOException e) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }


}
