package entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public abstract class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productID;

    @Column(nullable=false)
    private int stock;

    @Column(nullable=false)
    private BigDecimal price;

    @Column(nullable=false)
    private String productName;

    @Column(nullable=false)
    private String seriesName;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dateAdded;

    @Column(nullable = false, updatable = false)
    private ProductType productType;

    private String imgPath;

    @ManyToOne
    @JoinColumn(name = "useremail")
    private User user;

    @OneToMany(mappedBy = "productid")
    private List<OrderItem> orderItem;

    public enum ProductType{
        CARD,
        DECK,
        PACK
    }
}
