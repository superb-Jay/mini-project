package com.fast.miniproject.auth.dto;

import com.fast.miniproject.auth.entity.User;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ApiModel(value = "로그인")
public class LoginReqDTO {

    @ApiModelProperty(value = "이메일 ",required = true)
    private String email;

    @ApiModelProperty(value = "비밀번호 ",required = true)
    private String password;


    public LoginReqDTO(Claims claims){
        this.email=claims.get("email",String.class);
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
    }


}
