package com.fast.miniproject.product.service;

import com.fast.miniproject.auth.dto.UserDto;
import com.fast.miniproject.global.response.ResponseDTO;
import com.fast.miniproject.product.dto.WishlistAddRequestDTO;
import com.fast.miniproject.product.dto.WishlistDeleteRequestDTO;

public interface WishlistService {

    public ResponseDTO<?> listWishlistDTO(UserDto.LoginReqDTO loginReqDTO);

    public ResponseDTO<?> deleteWishlist(UserDto.LoginReqDTO loginReqDTO, WishlistDeleteRequestDTO requestDTO);

    public ResponseDTO<?> addWishlist(UserDto.LoginReqDTO loginReqDTO, WishlistAddRequestDTO requestDTO);

}
