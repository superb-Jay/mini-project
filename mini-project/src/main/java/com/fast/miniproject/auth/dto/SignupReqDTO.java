package com.fast.miniproject.auth.dto;

import com.fast.miniproject.auth.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@ApiModel(value = "회원가입")
public class SignupReqDTO {

    @ApiModelProperty(value = "이메일",required = true)
    private String email;
    @ApiModelProperty(value = "비밀번호",required = true)
    private String password;
    @ApiModelProperty(value = "이름",required = true)
    private String name;
    @ApiModelProperty(value = "생년월일",required = true)
    private String birth;
    @ApiModelProperty(value = "전화번호",required = true)
    private String phone;
    @ApiModelProperty(value = "연봉",required = true)
    private Long salary;
    @ApiModelProperty(value = "직업",required = true)
    private String job;

    public User toEntity(){

        return User.builder()
                .email(this.email)
                .password(this.password)
                .name(this.name)
                .birth(this.birth)
                .phone(this.phone)
                .salary(this.salary)
                .job(this.job)
                .build();
    }

}
