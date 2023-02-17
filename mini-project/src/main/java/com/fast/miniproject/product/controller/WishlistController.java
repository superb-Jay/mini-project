package com.fast.miniproject.product.controller;

import com.fast.miniproject.auth.dto.LoginReqDTO;
import com.fast.miniproject.global.response.ResponseDTO;
import com.fast.miniproject.product.dto.WishlistAddRequestDTO;
import com.fast.miniproject.product.dto.WishlistDeleteRequestDTO;
import com.fast.miniproject.product.service.BasketService;
import com.fast.miniproject.product.service.WishlistService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"위시리스트 기능"}, description = "상품 추가, 상품 삭제")
@RestController
@RequiredArgsConstructor
public class WishlistController {
    private final WishlistService wishlistService;
    private final BasketService basketService;

    @GetMapping("/wishlist")
    @ApiOperation(value = "위시리스트 (토큰 0)", notes = "상품 목록 조회")
    public ResponseDTO<?> listWishlist(@AuthenticationPrincipal LoginReqDTO loginReqDTO) {
        return wishlistService.listWishlistDTO(loginReqDTO);
    }

    @PostMapping("/wishlist/add")
    @ApiOperation(value = "위시리스트에 상품 추가 (토큰 0)", notes = "productId를 통한 상품 추가")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "productId", value = "productId", required = true)
    })
    public ResponseDTO<?> addWishlist(@AuthenticationPrincipal LoginReqDTO loginReqDTO, WishlistAddRequestDTO requestDTO) {
        return wishlistService.addWishlist(loginReqDTO, requestDTO);
    }

    @DeleteMapping("/wishlist/delete")
    @ApiOperation(value = "위시리스트에 상품 삭제 (토큰 0)", notes = "basketId를 통한 상품 삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "basketId", value = "basketId", required = true),
    })
    public ResponseDTO<?> deleteWishlist(@AuthenticationPrincipal LoginReqDTO loginReqDTO, WishlistDeleteRequestDTO requestDTO) {
        return wishlistService.deleteWishlist(loginReqDTO, requestDTO);
    }

    @PostMapping("/wishlist/addBasket")
    @ApiOperation(value = "위시리스트에서 장바구니에 추가 (토큰 0)", notes = "productId를 통한 상품 추가")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "productId", value = "productId", required = true)
    })
    public ResponseDTO<?> addBasketFromWishlist(@AuthenticationPrincipal LoginReqDTO loginReqDTO, WishlistAddRequestDTO requestDTO) {
        return wishlistService.addBasketFromWishlist(loginReqDTO, requestDTO);
    }

}
