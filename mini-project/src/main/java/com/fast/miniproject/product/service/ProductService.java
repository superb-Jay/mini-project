package com.fast.miniproject.product.service;

import com.fast.miniproject.global.response.ResponseDTO;
import com.fast.miniproject.product.dto.ProductDTO;

public interface ProductService {

    public ResponseDTO<?> selectProduct();
}
