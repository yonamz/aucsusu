package com.hyeonhwa.blog.springboot.web.dto.user;

import com.hyeonhwa.blog.springboot.domain.member.Role;
import com.hyeonhwa.blog.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

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
    private LocalDate reg_date;

    @Builder
    public UserSaveRequestDto(String uid, String name, String password,String email, int birth, Role role,LocalDate reg_date){
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
                .reg_date(LocalDate.now()).build();
    }


}
