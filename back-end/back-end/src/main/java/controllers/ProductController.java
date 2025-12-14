package controllers;

import dtos.ProductDTO;
import entities.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.ProductService;

import java.util.List;

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


}
