package com.fast.miniproject.auth.service.Impl;

import com.fast.miniproject.auth.dto.TokenDTO;
import com.fast.miniproject.auth.entity.RefreshToken;
import com.fast.miniproject.auth.jwt.JwtProvider;
import com.fast.miniproject.auth.repository.RefreshTokenRepository;
import com.fast.miniproject.auth.service.RefreshTokenService;
import com.fast.miniproject.global.response.ResponseDTO;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {


    private final JwtProvider jwtProvider;
    private final RefreshTokenRepository refreshTokenRepository;



    @Override
    public ResponseDTO<?> validateRefreshToken(String refreshToken) {
        try {
            RefreshToken updateRefreshToken = refreshTokenRepository.findByRefreshToken(refreshToken)
                    .orElseThrow(IllegalArgumentException::new);
            Claims claims = jwtProvider.tokenToClaims(updateRefreshToken.getRefreshToken());
            if (!claims.getExpiration().before(new Date())) {
                String updateAccessToken = jwtProvider.recreationAccessToken(claims.get("email").toString());
                return new ResponseDTO<>(200, "Refresh 토큰을 통한 Access Token 생성이 완료되었습니다",
                        TokenDTO.builder().accessToken(updateAccessToken).build());
            }else{
                throw new IllegalArgumentException();
            }

        }catch (IllegalArgumentException e) {
            return new ResponseDTO<>(402,"Refresh 토큰이 만료되었습니다. 로그인이 필요합니다.",null);
        }
    }


}
