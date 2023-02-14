package com.fast.miniproject.product.service;

import com.fast.miniproject.global.response.ResponseDTO;
import com.fast.miniproject.product.dto.ProductDTO;
public interface ProductService {

    ResponseDTO selectProductDetail(Long product_id);
    
    public ResponseDTO<?> selectProduct();

}
