package com.fast.miniproject.product.controller;

import com.fast.miniproject.auth.dto.LoginReqDTO;
import com.fast.miniproject.global.response.ResponseDTO;
import com.fast.miniproject.product.dto.BasketAddRequestDTO;
import com.fast.miniproject.product.dto.BasketDeleteRequestDTO;
import com.fast.miniproject.product.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BasketController {

    private final BasketService basketService;

    @GetMapping("/basket")
    public ResponseDTO<?> listBasket(@AuthenticationPrincipal LoginReqDTO loginReqDTO) {
        return basketService.listBasketDTO(loginReqDTO);
    }

    @DeleteMapping("/basket/delete")
    public ResponseDTO<?> deleteBasket(@AuthenticationPrincipal LoginReqDTO loginReqDTO,@RequestBody BasketDeleteRequestDTO request) {
        return basketService.deleteBasket(loginReqDTO, request);
    }

    @PostMapping("/basket/add")
    public ResponseDTO<?> addBasket(@AuthenticationPrincipal LoginReqDTO loginReqDTO,@RequestBody BasketAddRequestDTO requestDTO) {
        return basketService.addBasket(loginReqDTO ,requestDTO);
    }
}
