package com.fast.miniproject.auth.dto;

import com.fast.miniproject.auth.entity.User;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PatchUserResDTO {

    private String email;
    private String password;
    private String name;
    private String birth;
    private String phone;
    private Long salary;
    private String job;


    public PatchUserResDTO(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.name = user.getName();
        this.birth = user.getBirth();
        this.phone = user.getPhone();
        this.salary = user.getSalary();
        this.job = user.getJob();
    }
}
