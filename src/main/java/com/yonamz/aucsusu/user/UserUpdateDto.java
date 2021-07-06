package com.yonamz.aucsusu.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateDto {
    private String name;
    private String password;
    private String email;

    @Builder
    public UserUpdateDto(String name, String password, String email){
        this.name = name;
        this.password=password;
        this.email=email;
    }
}
