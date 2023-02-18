package com.fast.miniproject.product.service.impl;

import com.fast.miniproject.auth.dto.LoginReqDTO;
import com.fast.miniproject.auth.entity.User;
import com.fast.miniproject.auth.repository.UserRepository;
import com.fast.miniproject.global.response.ErrorResponseDTO;
import com.fast.miniproject.global.response.ResponseDTO;
import com.fast.miniproject.product.dto.WishlistAddRequestDTO;
import com.fast.miniproject.product.dto.WishlistDeleteRequestDTO;
import com.fast.miniproject.product.dto.WishlistResponseDTO;
import com.fast.miniproject.product.entity.Basket;
import com.fast.miniproject.product.entity.Product;
import com.fast.miniproject.product.entity.Wishlist;
import com.fast.miniproject.product.repository.BasketRepository;
import com.fast.miniproject.product.repository.ProductRepository;
import com.fast.miniproject.product.repository.WishlistRepository;
import com.fast.miniproject.product.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {

    private final BasketRepository basketRepository;
    private final WishlistRepository wishlistRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public ResponseDTO<?> listWishlistDTO(LoginReqDTO loginReqDTO) {
        User user = userRepository.findByEmail(loginReqDTO.getEmail()).get();
        List<Wishlist> wishlistList = wishlistRepository.findAllByUser(user);

        return new ResponseDTO<>(wishlistList.stream()
                .map(WishlistResponseDTO::new)
                .collect(Collectors.toList()));
    }

    @Override
    @Transactional
    public ResponseDTO<?> deleteWishlist(LoginReqDTO loginReqDTO, WishlistDeleteRequestDTO requestDTO) {
        try {
            User user = userRepository.findByEmail(loginReqDTO.getEmail()).get();
            if (wishlistRepository.existsByWishlistIdAndUser(requestDTO.getWishlistId(), user)) {
                wishlistRepository.deleteByWishlistIdAndUser(requestDTO.getWishlistId(), user);
                return new ResponseDTO<>(200, "찜 삭제 성공", null);
            }
        } catch (Exception e) {
            return new ErrorResponseDTO(500, "찜 삭제 실패").toResponse();
        }
        return new ErrorResponseDTO(500, "찜 삭제 실패1").toResponse();
    }

    @Override
    @Transactional
    public ResponseDTO<?> addWishlist(LoginReqDTO loginReqDTO, WishlistAddRequestDTO requestDTO) {
        Product product = productRepository.findByProductId(requestDTO.getProductId()).get();
        User user = userRepository.findByEmail(loginReqDTO.getEmail()).get();

        try {
            if (wishlistRepository.existsByProductAndUser(product,user)) {
                return new ErrorResponseDTO(500, "이미 찜 한 상품입니다.").toResponse();
            } else {
                wishlistRepository.save(new Wishlist(product, user));
            }
        } catch (Exception e) {
            return new ErrorResponseDTO(500, "찜 추가 실패").toResponse();
        }
        return new ResponseDTO<>(200, "찜 추가 성공", null);

    }

}
