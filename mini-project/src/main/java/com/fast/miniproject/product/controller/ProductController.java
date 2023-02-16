package com.fast.miniproject.product.controller;

import com.fast.miniproject.auth.dto.LoginReqDTO;
import com.fast.miniproject.product.dto.ProductIdList;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import com.fast.miniproject.global.response.ResponseDTO;
import com.fast.miniproject.product.service.ProductService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @GetMapping("/product/detail")
    public ResponseDTO<?> productDetail(@RequestParam Long products_id){
        return productService.selectProductDetail(products_id);
    }

    @GetMapping("/products")
    public ResponseDTO<?> selectProduct( ){
        return productService.selectProduct();
    }

    @GetMapping("/product/recommend")
    public ResponseDTO<?> recommendProduct(@AuthenticationPrincipal LoginReqDTO loginReqDTO){
        return productService.recommendProduct(loginReqDTO.getEmail());
    }

    @PostMapping("/products/buy")
    public ResponseDTO<?> buyProducts(@AuthenticationPrincipal LoginReqDTO user, ProductIdList products_id_list){
        return productService.buyProduct(products_id_list.getProducts_id_list(),user);
    }

    @GetMapping("/product/order/check")
    public ResponseDTO<?> orderCheck(@AuthenticationPrincipal LoginReqDTO dto){
         return productService.orderCheck(dto);
    }

    @DeleteMapping("/product/order/delete")
    public ResponseDTO<?> deleteBuy(@AuthenticationPrincipal LoginReqDTO dto,@RequestParam Long orderId){
        return productService.deleteOrder(dto,orderId);
    }
}
