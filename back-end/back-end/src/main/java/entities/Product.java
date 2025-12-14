package entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="productid")
    private Long productID;

    @Column(name="stock", nullable=false)
    private int stock;

    @Column(name="price", nullable=false)
    private BigDecimal price;

    @Column(name="productname", nullable=false)
    private String productName;

    @Column(name="seriesname", nullable=false)
    private String seriesName;

    @Column(name="dateadded", nullable = false, updatable = false)
    private LocalDate dateAdded;

    @Enumerated(EnumType.STRING)
    @Column(name="productype", nullable = false)
    private ProductType productType;

    @Column(name="imgpath")
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
