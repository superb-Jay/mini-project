package com.fast.miniproject.product.repository;

import com.fast.miniproject.auth.entity.User;
import com.fast.miniproject.product.entity.Product;
import com.fast.miniproject.product.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

    List<Wishlist> findAllByUser(User user);

    Boolean existsByWishlistIdAndUser(long wishlistId, User user);

    Boolean existsByProductAndUser(Product product, User user);

    void deleteByWishlistIdAndUser(long wishlistId, User user);
}
