package com.fast.miniproject.product.controller;

import com.fast.miniproject.auth.dto.UserDTO;
import com.fast.miniproject.global.response.ResponseDTO;
import com.fast.miniproject.product.dto.DeleteOrdersIdReqDTO;
import com.fast.miniproject.product.dto.ProductIdList;
import com.fast.miniproject.product.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import static com.fast.miniproject.search.controller.SearchProductController.PAGE_SIZE;

@Api(tags = {"상품 관련 서비스"}, description = "상품 전체 조회, 상품 상세 조회, 상품 추천, 주문, 주문 확인, 주문 취소")
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService productService;

    @ApiOperation(value = "상품 상세 정보 반환(토큰X)", notes = "상품 상세 정보를 반환하는 API")
    @GetMapping("/api/products/details")
    public ResponseDTO<?> productDetail(@RequestParam Long products_id) {
        return productService.selectProductDetail(products_id);
    }

    @ApiOperation(value = "상품 전체 리스트 반환", notes = "상품 전체 리스트 반환하는 API")
    @GetMapping("/api/products")
    public ResponseDTO<?> selectProduct(@RequestParam(required = false, defaultValue = "1") String page) {
        PageRequest pageRequest = null;
        try {
            int intPage = Integer.parseInt(page);
            pageRequest = PageRequest.of(intPage - 1, PAGE_SIZE);
        } catch (IllegalArgumentException e) {
            pageRequest = PageRequest.of(0, PAGE_SIZE);
        }
        return productService.selectProduct(pageRequest);
    }

    @ApiOperation(value = "상품 추천 리스트 반환", notes = "사용자가 가입 가능한 상품 리스트를 반환하는 API")
    @GetMapping("/api/products/recommends")
    public ResponseDTO<?> recommendProduct(@ApiIgnore @AuthenticationPrincipal UserDTO.LoginReqDTO loginReqDTO, @RequestParam(required = false, defaultValue = "1") String page) {
        PageRequest pageRequest = null;
        try {
            int intPage = Integer.parseInt(page);
            pageRequest = PageRequest.of(intPage - 1, PAGE_SIZE);
        } catch (IllegalArgumentException e) {
            pageRequest = PageRequest.of(0, PAGE_SIZE);
        }
        return productService.recommendProduct(loginReqDTO.getEmail(), pageRequest);
    }

    @PostMapping("/api/orders")
    @ApiOperation(value = "상품(들) 구매", notes = "상품(들)을 구매하는 API")

    public ResponseDTO<?> buyProducts(@ApiIgnore @AuthenticationPrincipal UserDTO.LoginReqDTO user, @RequestBody ProductIdList products_id_list) {
        return productService.buyProduct(products_id_list.getProducts_id_list(), user);
    }

    @GetMapping("/api/orders")
    @ApiOperation(value = "구매 기록 반환", notes = "상품을 구매한 기록을 반환하는 API")
    public ResponseDTO<?> orderCheck(@ApiIgnore @AuthenticationPrincipal UserDTO.LoginReqDTO dto) {
        return productService.orderCheck(dto);
    }

    @DeleteMapping("/api/orders")
    @ApiOperation(value = "주문 취소", notes = "주문을 취소하는 API")

    public ResponseDTO<?> deleteBuy(@ApiIgnore @AuthenticationPrincipal UserDTO.LoginReqDTO dto, @RequestBody DeleteOrdersIdReqDTO reqDTO){
        return productService.deleteOrder(dto,reqDTO.getOrderId());
    }
}
