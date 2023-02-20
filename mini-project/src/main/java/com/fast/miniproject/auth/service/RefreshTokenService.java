package com.fast.miniproject.auth.service;

import com.fast.miniproject.global.response.ResponseDTO;

public interface RefreshTokenService {

    public ResponseDTO<?> validateRefreshToken(String refreshToken);

}
