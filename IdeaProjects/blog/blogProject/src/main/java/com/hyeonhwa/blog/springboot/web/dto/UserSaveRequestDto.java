package com.hyeonhwa.blog.springboot.web.dto;

import com.hyeonhwa.blog.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSaveRequestDto {
    private String uid;
    private String user_name;
    private String password;

    @Builder
    public UserSaveRequestDto(String uid, String user_name, String password){
        this.uid=uid;
        this.user_name=user_name;
        this.password=password;
    }

    public User toEntity(){
        return User.builder()
                .uid(uid)
                .user_name(user_name)
                .password(password)
                .build();
    }
}
