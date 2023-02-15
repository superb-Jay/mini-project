package com.fast.miniproject.product.service;


import com.fast.miniproject.auth.dto.LoginReqDTO;
import com.fast.miniproject.global.response.ResponseDTO;
import com.fast.miniproject.product.dto.BasketAddRequestDTO;
import com.fast.miniproject.product.dto.BasketDeleteRequestDTO;
import com.fast.miniproject.product.dto.BasketResponseDTO;

import java.util.List;

public interface BasketService {

    public ResponseDTO<?> selectBasketDTO(LoginReqDTO loginReqDTO);

    public ResponseDTO<?> deleteBasket(LoginReqDTO loginReqDTO, BasketDeleteRequestDTO requestDTO);

    public ResponseDTO<?> addBasket(LoginReqDTO loginReqDTO, BasketAddRequestDTO requestDTO);


}
