package com.fast.miniproject.product.repository;

import com.fast.miniproject.global.response.ResponseDTO;
import com.fast.miniproject.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findByPriceLessThanEqual(int price);

}
