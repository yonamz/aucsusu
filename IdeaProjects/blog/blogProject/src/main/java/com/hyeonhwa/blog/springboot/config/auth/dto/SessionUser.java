package com.hyeonhwa.blog.springboot.config.auth.dto;

import com.hyeonhwa.blog.springboot.domain.member.Member;
import com.hyeonhwa.blog.springboot.domain.user.User;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;


    public SessionUser(User user){
        this.name=user.getName();
        this.email=user.getEmail();
    }
}

