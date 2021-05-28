package com.hyeonhwa.blog.springboot.web.dto;

import com.hyeonhwa.blog.springboot.domain.member.Role;
import com.hyeonhwa.blog.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
public class UserSaveRequestDto {
    private String uid;
    private String name;
    private String password;
    private String email;
    private int birth;
    private Role role;
    private String reg_date;

    @Builder
    public UserSaveRequestDto(String uid, String name, String password,String email, int birth, Role role,String reg_date){
        this.uid=uid;
        this.name=name;
        this.password=password;
        this.email=email;
        this.birth=birth;
        this.role=role;
        this.reg_date=reg_date;
    }

    public User toEntity(){
        return User.builder()
                .uid(uid)
                .name(name)
                .email(email)
                .password(password).birth(birth)
                .role(role)
                .reg_date("2021-05-26").build();
    }


}
