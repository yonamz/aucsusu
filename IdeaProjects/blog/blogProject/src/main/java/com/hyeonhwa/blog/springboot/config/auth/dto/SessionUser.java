package com.hyeonhwa.blog.springboot.config.auth.dto;

import com.hyeonhwa.blog.springboot.domain.member.Member;
import com.hyeonhwa.blog.springboot.domain.user.User;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
public class SessionUser implements Serializable {
    private String uid;
    private String name;
    private String password;
    private String email;
    private int birth;
    private LocalDate reg_date;

    public SessionUser(User user){
        this.uid=user.getUid();
        this.name=user.getName();
        this.password=user.getPassword();
        this.email=user.getEmail();
        this.birth=user.getBirth();
        this.reg_date= user.getReg_date();
    }
}

