package com.fast.miniproject.product.service;

import com.fast.miniproject.auth.dto.UserDto;
import com.fast.miniproject.auth.entity.User;
import com.fast.miniproject.global.response.ResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;

public interface ProductService {

    ResponseDTO<?> selectProductDetail(Long product_id);

    ResponseDTO<?> selectProduct(Pageable pageable);


    ResponseDTO<?> recommendProduct(String email, Pageable pageable);

    ResponseDTO<?> buyProduct(ArrayList<Integer> products_id_list, UserDto.LoginReqDTO user);

    ResponseDTO<?> orderCheck(UserDto.LoginReqDTO dto);

    ResponseDTO<?> deleteOrder(UserDto.LoginReqDTO dto, Long orderId);

    Long availableAmount(User user);
}
