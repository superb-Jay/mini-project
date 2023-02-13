package com.fast.miniproject.product.service;

import com.fast.miniproject.global.response.ResponseDTO;

public interface ProductService {

    ResponseDTO selectProductDetail(Long product_id);

}
