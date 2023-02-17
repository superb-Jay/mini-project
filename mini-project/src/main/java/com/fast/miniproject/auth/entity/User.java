package com.fast.miniproject.auth.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "member")
@Getter
@Setter
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
public class User {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(name = "member_email")
    private String email;

    @Column(name = "member_pw")
    private String password;

    @Column(name = "member_name")
    private String name;

    @Column(name = "member_birth")
    private String birth;

    @Column(name = "member_phone")
    private String phone;

    @Column(name = "member_salary")
    private Long salary;

    @Column(name = "member_job")
    private String job;

    @Column(name = "deleteCheck")
    private String deleteCheck;

    @CreationTimestamp
    @Column(name = "created_date")
    private LocalDateTime created_date;

    @UpdateTimestamp
    @Column(name = "updated_date")
    private LocalDateTime updated_date;

    public void update(String password, String phone, Long salary, String job) {
        this.password = password;
        this.phone = phone;
        this.salary = salary;
        this.job = job;
    }

    public void delete(String withdraw) {
        this.deleteCheck = withdraw;
    }

    @Builder
    public User(String email, String password, String name, String birth, String phone, Long salary, String job, String deleteCheck) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.birth = birth;
        this.phone = phone;
        this.salary = salary;
        this.job = job;
        this.deleteCheck = deleteCheck;
    }
}
