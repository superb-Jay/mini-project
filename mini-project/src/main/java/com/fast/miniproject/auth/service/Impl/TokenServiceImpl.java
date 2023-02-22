package com.fast.miniproject.auth.service.Impl;

import com.fast.miniproject.auth.dto.TokenDTO;
import com.fast.miniproject.auth.entity.Token;
import com.fast.miniproject.auth.jwt.JwtProvider;
import com.fast.miniproject.auth.repository.TokenRepository;
import com.fast.miniproject.auth.service.TokenService;
import com.fast.miniproject.global.config.RedisService;
import com.fast.miniproject.global.response.ErrorResponseDTO;
import com.fast.miniproject.global.response.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepository;
    private final JwtProvider jwtProvider;
    private final RedisService redisService;

    @Override
    public ResponseDTO<?> logout(String header){
        if (checkLogout(header)){
            return new ErrorResponseDTO(500,"이미 만료된 토큰입니다.").toResponse();
        }else {
            try {
                tokenRepository.save(Token.builder().token(header).build());
                return new ResponseDTO<>(200,"로그아웃 성공",header);
            } catch (Exception e) {
                return new ErrorResponseDTO(500,"로그아웃 시도중 에러가 발생 했습니다.").toResponse();
            }
        }
    }

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



    @Override
    public boolean checkLogout(String accessToken){
        return tokenRepository.existsByToken(accessToken);
    }

}