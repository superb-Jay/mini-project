package com.fast.miniproject.product.repository;

import com.fast.miniproject.auth.entity.Token;
import com.fast.miniproject.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {


}
