package com.fast.miniproject.product.service;


import com.fast.miniproject.auth.dto.UserDTO;
import com.fast.miniproject.global.response.ResponseDTO;
import com.fast.miniproject.product.dto.BasketAddRequestDTO;
import com.fast.miniproject.product.dto.BasketDeleteRequestDTO;

public interface BasketService {

    public ResponseDTO<?> listBasketDTO(UserDTO.LoginReqDTO loginReqDTO);

    public ResponseDTO<?> deleteBasket(UserDTO.LoginReqDTO loginReqDTO, BasketDeleteRequestDTO requestDTO);

    public ResponseDTO<?> addBasket(UserDTO.LoginReqDTO loginReqDTO, BasketAddRequestDTO requestDTO);


}
