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
@Table(name = "orderitems")
public class OrderItem {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="orderitemid")
    private Long orderItemId;

    @ManyToOne
    @JoinColumn(name="orderid", nullable = false, updatable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "productid", nullable = false, updatable = false)
    private Product product;

    @Column(name="quantity",nullable = false)
    private int quantity;

}
