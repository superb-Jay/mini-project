package com.fast.miniproject.product.repository;

import com.fast.miniproject.global.response.ResponseDTO;
import com.fast.miniproject.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Optional<Product> findByProductId(Long id);

}
