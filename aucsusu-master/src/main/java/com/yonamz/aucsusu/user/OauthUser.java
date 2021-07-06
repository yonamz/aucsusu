package com.yonamz.aucsusu.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class OauthUser{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String nickname;

    @Column
    private String picture;

    @Builder
    public OauthUser(String name, String email, String nickname){
        this.name = name;
        this.email = email;
        this.nickname=nickname;
    }

    public OauthUser update(String name){
        this.name=name;
        return this;
    }

}
