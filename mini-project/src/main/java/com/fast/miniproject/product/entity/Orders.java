package com.fast.miniproject.product.entity;

import com.fast.miniproject.auth.entity.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "member")
    private User user;

    @CreationTimestamp
    @Column(name = "order_date")
    private LocalDateTime purchaseDate;

    @Builder
    public Orders(User user) {
        this.user = user;
    }

    @OneToMany(
            mappedBy = "orders",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<PurchasedProduct> purchasedProducts = new ArrayList<>();

}
