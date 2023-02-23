package com.fast.miniproject.auth.service;

import com.fast.miniproject.auth.dto.UserDto;
import com.fast.miniproject.global.response.ResponseDTO;

public interface UserService {

    ResponseDTO<?> signup(UserDto.SignupReqDTO signupReqDTO);
    ResponseDTO<?> login(UserDto.LoginReqDTO loginReqDTO);
    public ResponseDTO<?> editUser(UserDto.LoginReqDTO loginReqDTO) ;
    public ResponseDTO<?> updateUser(UserDto.LoginReqDTO loginReqDTO, UserDto.PatchUserReqDTO patchUserReqDTO);
    public ResponseDTO<?> deleteUser(UserDto.LoginReqDTO loginReqDTO, UserDto.DeleteUserReqDTO deleteUserReqDTO);
}
