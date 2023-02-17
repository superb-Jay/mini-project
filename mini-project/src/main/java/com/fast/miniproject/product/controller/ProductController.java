package com.fast.miniproject.product.controller;

import com.fast.miniproject.auth.dto.LoginReqDTO;
import com.fast.miniproject.product.dto.ProductIdList;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import com.fast.miniproject.global.response.ResponseDTO;
import com.fast.miniproject.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = {"상품 관련 서비스"}, description = "상품 전체 조회, 상품 상세 조회, 상품 추천, 주문, 주문 확인, 주문 취소")
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @ApiOperation(value = "상품 상세 정보 반환(토큰X)", notes = "상품 상세 정보를 반환하는 API")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "products_id", value = "상품 ID", required = true)
    })
    @GetMapping("/product/detail")
    public ResponseDTO<?> productDetail(@RequestParam Long products_id){
        return productService.selectProductDetail(products_id);
    }

    @ApiOperation(value = "상품 전체 리스트 반환", notes = "상품 전체 리스트 반환하는 API")
    @GetMapping("/products")
    public ResponseDTO<?> selectProduct( ){
        return productService.selectProduct();
    }

    @ApiOperation(value = "상품 추천 리스트 반환", notes = "사용자가 가입 가능한 상품 리스트를 반환하는 API")
    @GetMapping("/product/recommend")
    public ResponseDTO<?> recommendProduct(@ApiIgnore @AuthenticationPrincipal LoginReqDTO loginReqDTO){
        return productService.recommendProduct(loginReqDTO.getEmail());
    }

    @PostMapping("/products/buy")
    @ApiOperation(value = "상품(들) 구매", notes = "상품(들)을 구매하는 API")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "products_id_list", value = "구매할 상품 ID 리스트", required = true)
    })
    public ResponseDTO<?> buyProducts(@ApiIgnore @AuthenticationPrincipal LoginReqDTO user, ProductIdList products_id_list){
        return productService.buyProduct(products_id_list.getProducts_id_list(),user);
    }

    @GetMapping("/product/order/check")
    @ApiOperation(value = "구매 기록 반환", notes = "상품을 구매한 기록을 반환하는 API")
    public ResponseDTO<?> orderCheck(@ApiIgnore @AuthenticationPrincipal LoginReqDTO dto){
         return productService.orderCheck(dto);
    }

    @DeleteMapping("/product/order/delete")
    @ApiOperation(value = "주문 취소", notes = "주문을 취소하는 API")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "주문 ID", required = true)
    })
    public ResponseDTO<?> deleteBuy(@ApiIgnore @AuthenticationPrincipal LoginReqDTO dto,@RequestParam Long orderId){
        return productService.deleteOrder(dto,orderId);
    }
}
