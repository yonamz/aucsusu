package com.hyeonhwa.blog.springboot.web.dto.user;

import com.hyeonhwa.blog.springboot.domain.member.Role;
import com.hyeonhwa.blog.springboot.domain.posts.Posts;
import com.hyeonhwa.blog.springboot.domain.user.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserResponseDto {
    private String uid;
    private String name;
    //private String password;
    private String email;
    private int birth;
    private Role role;
    private LocalDate reg_date;

    public UserResponseDto(User entity){
        this.uid = entity.getUid();
        this.name=entity.getName();
        this.email=entity.getEmail();
        this.birth=entity.getBirth();
        this.role=entity.getRole();
        this.reg_date=entity.getReg_date();
    }
}
