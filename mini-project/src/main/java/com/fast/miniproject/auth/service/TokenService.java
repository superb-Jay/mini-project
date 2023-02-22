package com.fast.miniproject.auth.service;

import com.fast.miniproject.global.response.ResponseDTO;

public interface TokenService {

    ResponseDTO<?> logout(String header);
    public ResponseDTO<?> validateRefreshToken(String refreshToken);
    boolean checkLogout(String token);
}
