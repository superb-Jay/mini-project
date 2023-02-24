package com.fast.miniproject.product.repository;

import com.fast.miniproject.product.entity.Orders;
import com.fast.miniproject.product.entity.PurchasedProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;

public interface PurchaseProductRepository extends JpaRepository<PurchasedProduct, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM purchased_product as p WHERE p.orders IN (:id_list)")
    ArrayList<PurchasedProduct> findAllByOrdersList(@Param("id_list") List<Orders> id_list);

    @Query(nativeQuery = true, value = "SELECT sum(p.purchased_product_price) FROM purchased_product as p WHERE p.orders IN (:id_list)")
    Integer searchSumByOrdersList(@Param("id_list") List<Orders> id_list);
}
