package com.fast.miniproject.auth.service;

import com.fast.miniproject.auth.dto.UserDTO;
import com.fast.miniproject.global.response.ResponseDTO;

public interface UserService {

    ResponseDTO<?> signup(UserDTO.SignupReqDTO signupReqDTO);
    ResponseDTO<?> login(UserDTO.LoginReqDTO loginReqDTO);
    public ResponseDTO<?> editUser(UserDTO.LoginReqDTO loginReqDTO) ;
    public ResponseDTO<?> updateUser(UserDTO.LoginReqDTO loginReqDTO, UserDTO.PatchUserReqDTO patchUserReqDTO);
    public ResponseDTO<?> deleteUser(UserDTO.LoginReqDTO loginReqDTO, UserDTO.DeleteUserReqDTO deleteUserReqDTO);
}
