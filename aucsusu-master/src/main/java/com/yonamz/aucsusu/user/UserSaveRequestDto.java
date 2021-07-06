package com.yonamz.aucsusu.user;

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
    private String phoneNumber;

    @Builder
    public UserSaveRequestDto(String uid, String name, String password, String email, String phoneNumber){
        this.uid=uid;
        this.name=name;
        this.password=password;
        this.email=email;
        this.phoneNumber=phoneNumber;
    }

    public User toEntity(){
        return User.builder()
                .uid(uid)
                .name(name)
                .email(email)
                .password(password).phoneNumber(phoneNumber).build();
    }

}
