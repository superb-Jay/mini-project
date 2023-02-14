package com.fast.miniproject.product.service.impl;

import com.fast.miniproject.auth.entity.User;
import com.fast.miniproject.auth.repository.UserRepository;
import com.fast.miniproject.global.response.ErrorResponseDTO;
import com.fast.miniproject.global.response.ResponseDTO;
import com.fast.miniproject.product.dto.ProductDTO;
import com.fast.miniproject.product.dto.ProductDetailDTO;
import com.fast.miniproject.product.entity.Product;
import com.fast.miniproject.product.repository.ProductRepository;
import com.fast.miniproject.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;


    private final UserRepository userRepository;

    @Override
    public ResponseDTO selectProductDetail(Long product_id) {
        try {
            Product product = productRepository.getReferenceById(product_id);
            ProductDetailDTO productDetailDTO = new ProductDetailDTO(product);
            return new ResponseDTO<>(productDetailDTO);

        }catch (Exception e){
            return new ErrorResponseDTO(500,"해당 상품의 상세정보를 찾지 못했습니다.").toResponse();
        }
    }

    @Override
    public ResponseDTO<?> selectProduct() {

        List<Product> product = productRepository.findAll();


        List<ProductDTO> productList = product.stream()
                .map(pro -> new ProductDTO(pro.getPrice(),pro.getBrand(),pro.getLogo(),pro.getName(),pro.getRate(),pro.getDetail()))
                .collect(Collectors.toList());

        return new ResponseDTO<>(productList);

    }

    @Override
    public ResponseDTO<?> recommendProduct(String email) {

        User user  = userRepository.findByEmail(email).get();

        int limitAmount =(int) (user.getSalary() * 2);

        List<Product> product = productRepository.findByPriceLessThanEqual(limitAmount);

        List<ProductDTO> productList = product.stream()
                .map(pro -> new ProductDTO(pro.getPrice(),pro.getBrand(),pro.getLogo(),pro.getName(),pro.getRate(),pro.getDetail()))
                .collect(Collectors.toList());




        return new ResponseDTO<>(productList);
    }

}
