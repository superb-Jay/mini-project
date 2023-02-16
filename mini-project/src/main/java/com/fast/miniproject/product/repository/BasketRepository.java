package com.fast.miniproject.product.repository;

import com.fast.miniproject.auth.entity.User;
import com.fast.miniproject.product.entity.Basket;
import com.fast.miniproject.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BasketRepository extends JpaRepository<Basket, Long> {

    void deleteByBasketIdAndUser(long basketId, User user);

    Boolean existsByBasketIdAndUser(long basketId, User user);

    Boolean existsByProductAndUser(Product product, User user);

    List<Basket> findAllByUser(User user);

}
