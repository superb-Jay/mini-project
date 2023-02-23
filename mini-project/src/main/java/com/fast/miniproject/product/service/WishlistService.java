package com.fast.miniproject.product.service;

import com.fast.miniproject.auth.dto.UserDTO;
import com.fast.miniproject.global.response.ResponseDTO;
import com.fast.miniproject.product.dto.WishlistAddRequestDTO;
import com.fast.miniproject.product.dto.WishlistDeleteRequestDTO;

public interface WishlistService {

    public ResponseDTO<?> listWishlistDTO(UserDTO.LoginReqDTO loginReqDTO);

    public ResponseDTO<?> deleteWishlist(UserDTO.LoginReqDTO loginReqDTO, WishlistDeleteRequestDTO requestDTO);

    public ResponseDTO<?> addWishlist(UserDTO.LoginReqDTO loginReqDTO, WishlistAddRequestDTO requestDTO);

}
