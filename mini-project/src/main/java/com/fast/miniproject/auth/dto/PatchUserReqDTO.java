package com.fast.miniproject.auth.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel(value = "회원정보수정 입력")
public class PatchUserReqDTO {

    @ApiModelProperty(value = "기존 비밀번호",required = true)
    private String oldPassword;
    @ApiModelProperty(value = "새로운 비밀번호")
    private String newPassword;
    @ApiModelProperty(value = "전화번호")
    private String phone;
    @ApiModelProperty(value = "연봉")
    private Long salary;
    @ApiModelProperty(value = "직업")
    private String job;

}
