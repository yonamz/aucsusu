package com.hyeonhwa.blog.springboot.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User {

    @Id
    private String uid;

    private String user_name;
    private String password;
    private String confirmpw;
    private String user_email;
    private int user_birth;
    private String reg_date;

    @Builder
    public User(String uid, String user_name, String password, String confirmpw,String user_email, int user_birth, String reg_date){
        this.uid=uid;
        this.user_name=user_name;
        this.password=password;
        this.confirmpw=confirmpw;
        this.user_email=user_email;
        this.user_birth=user_birth;
        this.reg_date=reg_date;
    }


   /* String uid;
    String user_name;
    String password;
    String confirmpw;
    String user_email;
    int user_birth;
    String reg_date;*/
}
