package com.fast.miniproject.product.service;


import com.fast.miniproject.auth.dto.UserDto;
import com.fast.miniproject.global.response.ResponseDTO;
import com.fast.miniproject.product.dto.BasketAddRequestDTO;
import com.fast.miniproject.product.dto.BasketDeleteRequestDTO;

public interface BasketService {

    public ResponseDTO<?> listBasketDTO(UserDto.LoginReqDTO loginReqDTO);

    public ResponseDTO<?> deleteBasket(UserDto.LoginReqDTO loginReqDTO, BasketDeleteRequestDTO requestDTO);

    public ResponseDTO<?> addBasket(UserDto.LoginReqDTO loginReqDTO, BasketAddRequestDTO requestDTO);


}
