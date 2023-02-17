package com.fast.miniproject.auth.dto;

import com.fast.miniproject.auth.entity.User;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SignupReqDTO {

    private String email;
    private String password;
    private String name;
    private String birth;
    private String phone;
    private Long salary;
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
