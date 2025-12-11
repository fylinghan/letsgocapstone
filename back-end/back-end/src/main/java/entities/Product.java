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
    private String name;

    @Column(nullable=false)
    private String seriesName;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dateAdded;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;

    @Column(nullable = false, updatable = false)
    private ProductType productType;

    @OneToMany(mappedBy = "productid")
    private List<OrderItem> orderItem;

    private String imgPath;

    public enum ProductType{
        CARD,
        DECK,
        PACK
    }
}
