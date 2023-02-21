package com.fast.miniproject.auth.service.Impl;

import com.fast.miniproject.auth.dto.TokenDTO;
import com.fast.miniproject.auth.jwt.JwtProvider;
import com.fast.miniproject.auth.service.RefreshTokenService;
import com.fast.miniproject.global.response.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {


    private final JwtProvider jwtProvider;
    private final RedisService redisService;

    @Override
    public ResponseDTO<?> validateRefreshToken(String refreshToken) {
        try {
            String userEmail = redisService.getData(refreshToken);

            if (jwtProvider.getExpiration(refreshToken) > 0) {
                String updateAccessToken = jwtProvider.recreationAccessToken(userEmail);
                return new ResponseDTO<>(200, "Refresh 토큰을 통한 Access Token 생성이 완료되었습니다",
                        TokenDTO.builder().accessToken(updateAccessToken).refreshToken(refreshToken).build());
            }else{
                throw new IllegalArgumentException();
            }

        }catch (IllegalArgumentException | NullPointerException e) {
            return new ResponseDTO<>(403,"Refresh 토큰이 유효하지 않습니다. 로그인이 필요합니다.",null);
        }
    }


}
