package com.fast.miniproject.product.service.Impl;


import com.fast.miniproject.auth.dto.LoginReqDTO;
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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public ResponseDTO<?> selectBasketDTO(LoginReqDTO loginReqDTO) {
        List<Basket> basketList = basketRepository.findAll();
        return new ResponseDTO<>(basketList.stream().map(en -> new BasketResponseDTO(en))
                .collect(Collectors.toList()));
    }

    @Override
    @Transactional
    public ResponseDTO<?> deleteBasket(LoginReqDTO loginReqDTO, BasketDeleteRequestDTO requestDTO) {

        try {
            basketRepository.deleteById(requestDTO.getBasketId());
        } catch (Exception e) {
            return new ErrorResponseDTO(500, "상품 삭제 실패").toResponse();
        }
        return new ResponseDTO<>(200,"상품 삭제 성공",null);
    }

    @Override
    public ResponseDTO<?> addBasket(LoginReqDTO loginReqDTO, BasketAddRequestDTO requestDTO) {

        Product product = productRepository.findByProductId(requestDTO.getProductId()).get();
        User user = userRepository.findByEmail(loginReqDTO.getEmail()).get();

        try {
          basketRepository.save(new Basket(product, user));
        } catch (Exception e) {
            return new ErrorResponseDTO(500, "상품 추가 실패").toResponse();
        }
        return new ResponseDTO<>(200, "상품 추가 성공", null);

        }
}
