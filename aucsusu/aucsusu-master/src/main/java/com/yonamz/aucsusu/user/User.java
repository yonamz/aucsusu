package com.yonamz.aucsusu.user;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String uid;

    @Column
    private String name;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private String phoneNumber;


    @Builder
    public User(String uid, String name, String password,String email, String phoneNumber){
        this.uid=uid;
        this.name=name;
        this.password=password;
        this.email=email;
        this.phoneNumber=phoneNumber;
    }

    public void update(String name,String password,String email) {
        this.name=name;
        this.password=password;
        this.email=email;
    }
}
