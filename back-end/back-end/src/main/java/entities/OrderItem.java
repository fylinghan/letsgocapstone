package entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orderitem")
public class OrderItem {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;

    @ManyToOne
    @Column(name="orderid", nullable = false, updatable = false)
    private Order orderId;

    @ManyToOne
    @Column(name = "productid", nullable = false, updatable = false)
    private Product product;

    @Column(name="quantity")
    private int quantity;

}
