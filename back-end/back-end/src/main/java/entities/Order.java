package entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="orderid")
    private Long orderId;

    @Enumerated(EnumType.STRING)
    @Column(name="shippingstatus", nullable = false)
    private status shippingStatus;

    @Column(name = "orderdate", nullable = false)
    private LocalDate orderDate;

    @Column(name = "shippingaddress", nullable = false)
    private String shippingAddress;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "useremail")
    private User user;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderedItems;

    public enum status {
        ORDERED,
        SHIPPED,
        DELIVERED
    }
}
