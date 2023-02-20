package com.fast.miniproject.product.service;

import com.fast.miniproject.auth.dto.LoginReqDTO;
import com.fast.miniproject.global.response.ResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;

public interface ProductService {

    ResponseDTO<?> selectProductDetail(Long product_id);

    ResponseDTO<?> selectProduct(Pageable pageable);


    ResponseDTO<?> recommendProduct(String email, Pageable pageable);

    ResponseDTO<?> buyProduct(ArrayList<Integer> products_id_list, LoginReqDTO user);

    ResponseDTO<?> orderCheck(LoginReqDTO dto);

    ResponseDTO<?> deleteOrder(LoginReqDTO dto, Long orderId);
}
