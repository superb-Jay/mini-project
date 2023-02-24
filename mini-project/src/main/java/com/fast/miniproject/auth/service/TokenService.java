package com.fast.miniproject.auth.service;

import com.fast.miniproject.auth.dto.TokenDTO;
import com.fast.miniproject.global.response.ResponseDTO;

public interface TokenService {

    ResponseDTO<?> logout(String header, TokenDTO.RefreshTokenReqDTO refreshTokenReqDTO);

    ResponseDTO<?> validateRefreshToken(String refreshToken);

    boolean checkToken(String token);
}
