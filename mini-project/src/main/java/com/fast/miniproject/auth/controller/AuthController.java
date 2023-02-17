package com.fast.miniproject.auth.controller;


import com.fast.miniproject.auth.dto.LoginReqDTO;
import com.fast.miniproject.auth.dto.PatchUserReqDTO;
import com.fast.miniproject.auth.dto.SignupReqDTO;
import com.fast.miniproject.auth.dto.PatchUserResDTO;
import com.fast.miniproject.auth.service.TokenService;
import com.fast.miniproject.auth.service.UserService;
import com.fast.miniproject.global.response.ResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;


@Api(tags = {"회원정보 서비스"}, description = "회원가입, 회원정보수정, 회원탈퇴,")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final TokenService tokenService;

    @PostMapping("/register")
    @ApiOperation(value = "회원가입 (토큰 X)" , notes = "정보를 입력받아 회원가입을 진행하고 DB에 저장하는")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", value = "사용자 식별 이메일", required = true),
            @ApiImplicitParam(name = "password", value = "사용자 비밀번호", required = true),
            @ApiImplicitParam(name = "name", value = "사용자 이름", required = true),
            @ApiImplicitParam(name = "birth", value = "사용자 생년월일)", required = true),
            @ApiImplicitParam(name = "phone", value = "사용자 전화번호", required = true),
            @ApiImplicitParam(name = "salary", value = "사용자 연봉", required = true),
            @ApiImplicitParam(name = "job", value = "사용자 직업", required = true)
    })
    public ResponseDTO<?> signUp(
            @RequestBody SignupReqDTO signupReqDTO) {
        return userService.signup(signupReqDTO);
    }

    @PostMapping("/login")
    @ApiOperation(value = "로그인 (토큰 X)", notes = "이메일과 패스워드를 입력받아 로그인이 가능 성공하면 토큰발급")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", value = "사용자 식별 이메일", required = true),
            @ApiImplicitParam(name = "password", value = "사용자 비밀번호", required = true),
    })
    public ResponseDTO<?> signIn(
            @RequestBody LoginReqDTO loginReqDTO) {
        return userService.login(loginReqDTO);
    }

    @PostMapping("/logout")
    @ApiOperation(value = "로그아웃 (토큰 O)", notes = "버튼을 누르면 현재 로그인 토큰을 로그아웃 테이블에 저장한다. " +
                                            "다음 요청시에 현재 토큰과 요청이 오면 토큰 유효성 검사에 걸려서 로그인을 다시 요청하게 된다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "header", value = "현재 헤더에 있는 토큰의 정보", required = true)
    })

    public ResponseDTO<?> logout(@ApiIgnore @RequestHeader(name = "Authorization") String header) {
        return tokenService.logout(header);
    }
    @PostMapping("/api/user")
    @ApiOperation(value = "회원정보 수정페이지 (토큰 O)", notes = "회원정보 수정페이지로 이동한다. " +
                        "현재 로그인회원의 정보를 반환한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", value = "사용자 식별 이메일", required = true),
            @ApiImplicitParam(name = "password", value = "사용자 비밀번호", required = true),
    })
    public ResponseDTO<?> editUser(
            @ApiIgnore
            @RequestBody
            @AuthenticationPrincipal
            LoginReqDTO loginReqDTO) {
        return userService.editUser(loginReqDTO);
    }

    @PatchMapping("/api/user/update")
    @ApiOperation(value = "회원정보 수정버튼 (토큰 O)", notes = "기존 비밀번호가 맞다면 DB에 저장한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "oldPassword", value = "기존 비밀번호", required = true),
            @ApiImplicitParam(name = "newPassword", value = "새로운 비밀번호", required = true),
            @ApiImplicitParam(name = "phone", value = "전화번호", required = true),
            @ApiImplicitParam(name = "salary", value = "연봉", required = true),
            @ApiImplicitParam(name = "job", value = "직업", required = true),
    })
    public ResponseDTO<?> updateUser(
            @ApiIgnore
            @RequestBody
            @AuthenticationPrincipal LoginReqDTO loginReqDTO, PatchUserReqDTO patchUserReqDTO) {
        return userService.updateUser(loginReqDTO,patchUserReqDTO);
    }

    @DeleteMapping("/api/user/delete")
    @ApiOperation(value = "회원탈퇴 버튼 (토큰 O)", notes = "기존 비밀번호가 맞다면 DB에 deleteCheck에 withdraw를 기록하고 " +
            "로그인시 deleteCheck 값이 null이 아니면 탈퇴한 회원이라는 메세지를 안내")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "password", value = "비밀번호", required = true),
    })
    public ResponseDTO<?> deleteUser(
            @ApiIgnore
            @RequestBody
            @AuthenticationPrincipal
            LoginReqDTO loginReqDTO, String password) {
        return userService.deleteUser(loginReqDTO,password);
    }

}
