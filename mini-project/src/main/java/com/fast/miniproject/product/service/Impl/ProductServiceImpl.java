package com.fast.miniproject.product.service.Impl;

import com.fast.miniproject.auth.entity.User;
import com.fast.miniproject.global.response.ResponseDTO;
import com.fast.miniproject.product.dto.ProductDTO;
import com.fast.miniproject.product.entity.Product;
import com.fast.miniproject.product.repository.ProductRepository;
import com.fast.miniproject.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;



    @Override
    public ResponseDTO<?> selectProduct() {

       List<Product> product = productRepository.findAll();


                List<ProductDTO> productList = product.stream()
                .map(pro -> new ProductDTO(pro.getPrice(),pro.getBrand(),pro.getLogo(),pro.getName(),pro.getRate(),pro.getDetail()))
                .collect(Collectors.toList());
        
        return new ResponseDTO<>(productList);

    }
}
