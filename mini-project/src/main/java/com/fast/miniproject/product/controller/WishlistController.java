package com.fast.miniproject.product.controller;

import com.fast.miniproject.auth.dto.UserDTO;
import com.fast.miniproject.global.response.ResponseDTO;
import com.fast.miniproject.product.dto.WishlistAddRequestDTO;
import com.fast.miniproject.product.dto.WishlistDeleteRequestDTO;
import com.fast.miniproject.product.service.BasketService;
import com.fast.miniproject.product.service.WishlistService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"위시리스트 기능"}, description = "상품 추가, 상품 삭제")
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class WishlistController {
    private final WishlistService wishlistService;
    private final BasketService basketService;

    @GetMapping("/api/wishlists")
    @ApiOperation(value = "위시리스트 (토큰 0)", notes = "상품 목록 조회")
    public ResponseDTO<?> listWishlist(@AuthenticationPrincipal UserDTO.LoginReqDTO loginReqDTO) {
        return wishlistService.listWishlistDTO(loginReqDTO);
    }

    @PostMapping("/api/wishlists")
    @ApiOperation(value = "위시리스트에 상품 추가 (토큰 0)", notes = "productId를 통한 상품 추가")

    public ResponseDTO<?> addWishlist(@AuthenticationPrincipal UserDTO.LoginReqDTO loginReqDTO, @RequestBody WishlistAddRequestDTO requestDTO) {
        return wishlistService.addWishlist(loginReqDTO, requestDTO);
    }

    @DeleteMapping("/api/wishlists")
    @ApiOperation(value = "위시리스트에 상품 삭제 (토큰 0)", notes = "basketId를 통한 상품 삭제")

    public ResponseDTO<?> deleteWishlist(@AuthenticationPrincipal UserDTO.LoginReqDTO loginReqDTO, @RequestBody WishlistDeleteRequestDTO requestDTO) {
        return wishlistService.deleteWishlist(loginReqDTO, requestDTO);
    }


}
