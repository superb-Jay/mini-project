package com.fast.miniproject.product.controller;

import com.fast.miniproject.global.response.ResponseDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {



    @GetMapping("/products")
    public ResponseDTO productDetail(@RequestParam Long products_id){
        return null;
    }
}
