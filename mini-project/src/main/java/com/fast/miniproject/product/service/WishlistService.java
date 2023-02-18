package com.fast.miniproject.product.service;

import com.fast.miniproject.auth.dto.LoginReqDTO;
import com.fast.miniproject.global.response.ResponseDTO;
import com.fast.miniproject.product.dto.WishlistAddRequestDTO;
import com.fast.miniproject.product.dto.WishlistDeleteRequestDTO;

public interface WishlistService {

    public ResponseDTO<?> listWishlistDTO(LoginReqDTO loginReqDTO);

    public ResponseDTO<?> deleteWishlist(LoginReqDTO loginReqDTO, WishlistDeleteRequestDTO requestDTO);

    public ResponseDTO<?> addWishlist(LoginReqDTO loginReqDTO, WishlistAddRequestDTO requestDTO);

}
