package com.fast.miniproject.product.controller;

import com.fast.miniproject.global.response.ResponseDTO;
import com.fast.miniproject.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @GetMapping("/products")
    public ResponseDTO productDetail(@RequestParam Long products_id){
        return productService.selectProductDetail(products_id);
    }
}
