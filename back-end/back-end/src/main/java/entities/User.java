package entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "email", unique = true, nullable = false, updatable = false)
    private String email;

    @Column(name="password")
    private String password;

    @OneToMany(mappedBy = "userid")
    private List<Order> orders;

    @OneToMany(mappedBy = "userid")
    private List<Product> cards;
}
