package com.fast.miniproject.auth.controller;


import com.fast.miniproject.auth.dto.TokenDTO;
import com.fast.miniproject.auth.dto.UserDTO;
import com.fast.miniproject.auth.service.TokenService;
import com.fast.miniproject.auth.service.UserService;
import com.fast.miniproject.global.response.ResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;


@Api(tags = {"회원정보 서비스"}, description = "회원가입, 회원정보수정, 회원탈퇴, 토큰 리프레시")
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserService userService;
    private final TokenService tokenService;

    @PostMapping("/api/register")
    @ApiOperation(value = "회원가입 (토큰 X)", notes = "정보를 입력받아 회원가입을 진행하고 DB에 저장하는")

    public ResponseDTO<?> signUp(@RequestBody UserDTO.SignupReqDTO signupReqDTO) {
        return userService.signup(signupReqDTO);
    }

    @PostMapping("/api/login")
    @ApiOperation(value = "로그인 (토큰 X)", notes = "이메일과 패스워드를 입력받아 로그인이 가능 성공하면 토큰발급")
    public ResponseDTO<?> signIn(@RequestBody UserDTO.LoginReqDTO loginReqDTO) {
        return userService.login(loginReqDTO);
    }

    @PostMapping("/api/logout")
    @ApiOperation(value = "로그아웃 (토큰 O)", notes = "버튼을 누르면 현재 로그인 토큰을 로그아웃 테이블에 저장한다. " +
                                            "다음 요청시에 현재 토큰과 요청이 오면 토큰 유효성 검사에 걸려서 로그인을 다시 요청하게 된다.")
    public ResponseDTO<?> logout(@ApiIgnore @RequestHeader(name="Authorization") String header,@RequestBody TokenDTO.RefreshTokenReqDTO refreshTokenReqDTO) {
        return tokenService.logout(header,refreshTokenReqDTO);
    }

    @GetMapping("/api/user")
    @ApiOperation(value = "회원정보 수정페이지 (토큰 O)", notes = "회원정보 수정페이지로 이동한다. " +
                        "현재 로그인회원의 정보를 반환한다.")
    public ResponseDTO<?> editUser(@AuthenticationPrincipal UserDTO.LoginReqDTO loginReqDTO) {
        return userService.editUser(loginReqDTO);
    }

    @PatchMapping("/api/user")
    @ApiOperation(value = "회원정보 수정버튼 (토큰 O)", notes = "기존 비밀번호가 맞다면 DB에 저장한다.")
    public ResponseDTO<?> updateUser(@AuthenticationPrincipal UserDTO.LoginReqDTO loginReqDTO, @RequestBody UserDTO.PatchUserReqDTO patchUserReqDTO) {
        return userService.updateUser(loginReqDTO,patchUserReqDTO);
    }

    @DeleteMapping("/api/user")
    @ApiOperation(value = "회원탈퇴 버튼 (토큰 O)", notes = "기존 비밀번호가 맞다면 DB에 deleteCheck에 withdraw를 기록하고 " +
            "로그인시 deleteCheck 값이 null이 아니면 탈퇴한 회원이라는 메세지를 안내")
    public ResponseDTO<?> deleteUser(@AuthenticationPrincipal UserDTO.LoginReqDTO loginReqDTO, @RequestBody UserDTO.DeleteUserReqDTO deleteUserReqDTO) {
        return userService.deleteUser(loginReqDTO,deleteUserReqDTO);
    }

    @PostMapping("/api/refresh")
    @ApiOperation(value = "토큰 리프레시", notes = "리프레시 토큰을 보내주면 확인하고 엑세스토큰을 새로 발급")
    public ResponseDTO<?> validateRefreshToken(@RequestBody TokenDTO.RefreshTokenReqDTO refreshTokenReqDTO){
        return tokenService.validateRefreshToken(refreshTokenReqDTO.getRefreshToken());

    }

}
