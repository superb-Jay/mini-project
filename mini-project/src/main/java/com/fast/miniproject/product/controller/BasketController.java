package com.fast.miniproject.product.controller;

import com.fast.miniproject.auth.dto.UserDto;
import com.fast.miniproject.global.response.ResponseDTO;
import com.fast.miniproject.product.dto.BasketAddRequestDTO;
import com.fast.miniproject.product.dto.BasketDeleteRequestDTO;
import com.fast.miniproject.product.service.BasketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"장바구니 기능"}, description = "상품 추가, 상품 삭제")
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class BasketController {

    private final BasketService basketService;

    @GetMapping("/api/baskets")
    @ApiOperation(value = "장바구니 (토큰 0)", notes = "상품 목록 조회")
    public ResponseDTO<?> listBasket(@AuthenticationPrincipal UserDto.LoginReqDTO loginReqDTO) {
        return basketService.listBasketDTO(loginReqDTO);
    }

    @DeleteMapping("/api/baskets")
    @ApiOperation(value = "장바구니에 상품 삭제 (토큰 0)", notes = "basketId를 통한 상품 삭제")

    public ResponseDTO<?> deleteBasket(@AuthenticationPrincipal UserDto.LoginReqDTO loginReqDTO, @RequestBody BasketDeleteRequestDTO request) {
        return basketService.deleteBasket(loginReqDTO, request);
    }

    @PostMapping("/api/baskets")
    @ApiOperation(value = "장바구니에 상품 추가 (토큰 0)", notes = "productId를 통한 상품 추가")

    public ResponseDTO<?> addBasket(@AuthenticationPrincipal UserDto.LoginReqDTO loginReqDTO, @RequestBody BasketAddRequestDTO requestDTO) {
        return basketService.addBasket(loginReqDTO ,requestDTO);
    }
}
