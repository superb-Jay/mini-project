package com.fast.miniproject.product.controller;

import com.fast.miniproject.auth.dto.LoginReqDTO;
import com.fast.miniproject.global.response.ResponseDTO;
import com.fast.miniproject.product.dto.WishlistAddRequestDTO;
import com.fast.miniproject.product.dto.WishlistDeleteRequestDTO;
import com.fast.miniproject.product.service.BasketService;
import com.fast.miniproject.product.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WishlistController {
    private final WishlistService wishlistService;
    private final BasketService basketService;

    @GetMapping("/wishlist")
    public ResponseDTO<?> listWishlist(@AuthenticationPrincipal LoginReqDTO loginReqDTO) {
        return wishlistService.listWishlistDTO(loginReqDTO);
    }

    @PostMapping("/wishlist/add")
    public ResponseDTO<?> addWishlist(@AuthenticationPrincipal LoginReqDTO loginReqDTO, WishlistAddRequestDTO requestDTO) {
        return wishlistService.addWishlist(loginReqDTO, requestDTO);
    }

    @DeleteMapping("/wishlist/delete")
    public ResponseDTO<?> deleteWishlist(@AuthenticationPrincipal LoginReqDTO loginReqDTO, WishlistDeleteRequestDTO requestDTO) {
        return wishlistService.deleteWishlist(loginReqDTO, requestDTO);
    }

    @PostMapping("/wishlist/addBasket")
    public ResponseDTO<?> addBasketFromWishlist(@AuthenticationPrincipal LoginReqDTO loginReqDTO, WishlistAddRequestDTO requestDTO) {
        return wishlistService.addBasketFromWishlist(loginReqDTO, requestDTO);
    }

}
