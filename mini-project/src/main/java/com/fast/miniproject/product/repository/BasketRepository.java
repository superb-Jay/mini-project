package com.fast.miniproject.product.repository;

import com.fast.miniproject.auth.entity.User;
import com.fast.miniproject.product.entity.Basket;
import com.fast.miniproject.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BasketRepository extends JpaRepository<Basket, Long> {

    void deleteByBasketIdAndUser(long basketId, User user);

    Boolean existsByBasketIdAndUser(long basketId, User user);

    Boolean existsByProductAndUser(Product product, User user);

    List<Basket> findAllByUser(User user);

    @Modifying
    @Query(nativeQuery = true ,value = "DELETE FROM basket WHERE member =:member AND product IN (:id_list)")
    void deleteByUserAndProductList(@Param("member") Long member,@Param("id_list") List<Integer> id_list);


}
