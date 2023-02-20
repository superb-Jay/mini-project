package com.fast.miniproject.product.repository;

import com.fast.miniproject.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findByPriceLessThanEqual(int price, Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT * FROM product as p WHERE p.product_id IN (:id_list)")
    List<Product> findAllByProductId(@Param("id_list") List<Integer> id_list);

    Optional<Product> findByProductId(Long id);

    Page<Product> findAll(Pageable pageable);
}