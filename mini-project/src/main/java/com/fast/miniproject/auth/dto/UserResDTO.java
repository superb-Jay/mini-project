package com.fast.miniproject.auth.dto;

import com.fast.miniproject.auth.entity.User;
import lombok.*;

import javax.persistence.Column;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserResDTO {

    private Long memberId;
    private String email;
    private String password;
    private String name;
    private int age;
    private String gender;
    private String phone;
    private Long salary;
    private String job;


    public UserResDTO(User user) {
        this.memberId = user.getMemberId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.name = user.getName();
        this.age = user.getAge();
        this.gender = user.getGender();
        this.phone = user.getPhone();
        this.salary = user.getSalary();
        this.job = user.getJob();
    }
}
