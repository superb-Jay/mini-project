package com.fast.miniproject.product.entity;

import com.fast.miniproject.auth.entity.User;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
@Table(name = "wishlist")
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wish_id")
    private Long wishlistId;

    @ManyToOne(targetEntity = Product.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "product")
    private Product product;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "member")
    private User user;

    @Builder
    public Wishlist(Product product, User user) {
        this.product = product;
        this.user = user;
    }
}
