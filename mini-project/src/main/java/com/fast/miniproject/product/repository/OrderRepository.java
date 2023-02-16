package com.fast.miniproject.product.repository;

import com.fast.miniproject.auth.entity.User;
import com.fast.miniproject.product.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Orders,Long> {


    List<Orders> findAllByUserOrderByPurchaseDate(User user);
    Optional<Orders> findByOrderIdAndUser(Long orderId,User user);

}
