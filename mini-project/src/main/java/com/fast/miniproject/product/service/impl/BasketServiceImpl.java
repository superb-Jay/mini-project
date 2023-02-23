package com.fast.miniproject.product.service.impl;


import com.fast.miniproject.auth.dto.UserDTO;
import com.fast.miniproject.auth.entity.User;
import com.fast.miniproject.auth.repository.UserRepository;
import com.fast.miniproject.global.response.ErrorResponseDTO;
import com.fast.miniproject.global.response.ResponseDTO;
import com.fast.miniproject.product.dto.BasketAddRequestDTO;
import com.fast.miniproject.product.dto.BasketDeleteRequestDTO;
import com.fast.miniproject.product.dto.BasketResponseDTO;
import com.fast.miniproject.product.entity.Basket;
import com.fast.miniproject.product.entity.Product;
import com.fast.miniproject.product.repository.BasketRepository;
import com.fast.miniproject.product.repository.ProductRepository;
import com.fast.miniproject.product.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public ResponseDTO<?> listBasketDTO(UserDTO.LoginReqDTO loginReqDTO) {
        User user = userRepository.findByEmail(loginReqDTO.getEmail()).get();
        List<Basket> basketList = basketRepository.findAllByUser(user);

        return new ResponseDTO<>(basketList.stream()
                .map(BasketResponseDTO::new)
                .collect(Collectors.toList()));
    }

    @Override
    @Transactional
    public ResponseDTO<?> deleteBasket(UserDTO.LoginReqDTO loginReqDTO, BasketDeleteRequestDTO requestDTO) {
        try {
            User user = userRepository.findByEmail(loginReqDTO.getEmail()).get();
            if (basketRepository.existsByBasketIdAndUser(requestDTO.getBasketId(), user)) {
                basketRepository.deleteByBasketIdAndUser(requestDTO.getBasketId(), user);
                return new ResponseDTO<>(200,"상품 삭제 성공",null);
            }
        } catch (Exception e) {
            return new ErrorResponseDTO(500, "상품 삭제 실패").toResponse();
        }
        return new ErrorResponseDTO(500, "상품 삭제 실패1").toResponse();
    }

    @Override
    @Transactional
    public ResponseDTO<?> addBasket(UserDTO.LoginReqDTO loginReqDTO, BasketAddRequestDTO requestDTO) {

        Product product = productRepository.findByProductId(requestDTO.getProductId()).get();

        User user = userRepository.findByEmail(loginReqDTO.getEmail()).get();

        try {
            if (basketRepository.existsByProductAndUser(product,user)) {
                return new ErrorResponseDTO(500, "이미 존재하는 상품입니다.").toResponse();
            }  else {
                basketRepository.save(new Basket(product, user));
            }
        } catch (Exception e) {
            return new ErrorResponseDTO(500, "상품 추가 실패").toResponse();
        }
        return new ResponseDTO<>(200, "상품 추가 성공", null);

        }
}
