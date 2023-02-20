package com.fast.miniproject.auth.dto;

import com.fast.miniproject.auth.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel(value = "회원정보수정 출력")
public class PatchUserResDTO {

    @ApiModelProperty(value = "이메일")
    private String email;
    @ApiModelProperty(value = "비밀번호")
    private String password;
    @ApiModelProperty(value = "이름")
    private String name;
    @ApiModelProperty(value = "생년월일")
    private String birth;
    @ApiModelProperty(value = "전화번호")
    private String phone;
    @ApiModelProperty(value = "연봉")
    private Long salary;
    @ApiModelProperty(value = "직업")
    private String job;
    private Long availableAmount;


    public PatchUserResDTO(User user,Long availableAmount) {
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.name = user.getName();
        this.birth = user.getBirth();
        this.phone = user.getPhone();
        this.salary = user.getSalary();
        this.job = user.getJob();
        this.availableAmount = availableAmount;
    }
}
