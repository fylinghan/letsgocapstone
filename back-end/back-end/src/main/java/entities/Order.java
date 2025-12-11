package entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Enumerated(EnumType.STRING)
    private status shippingStatus;

    private LocalDateTime orderDate;

    private String shippingAddress;

    public enum status {
        ORDERED,
        SHIPPED,
        DELIVERED
    }

    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;

    @OneToMany(mappedBy = "orderid")
    private List<OrderItem> orderedItems;

}
