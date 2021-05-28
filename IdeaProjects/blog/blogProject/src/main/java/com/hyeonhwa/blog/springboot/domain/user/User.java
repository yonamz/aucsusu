package com.hyeonhwa.blog.springboot.domain.user;

import com.hyeonhwa.blog.springboot.domain.BaseTimeEntity;
import com.hyeonhwa.blog.springboot.domain.member.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@Entity
public class User implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String uid;

    @Column
    private String name;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private int birth;

    @Enumerated(EnumType.STRING)
    @Column
    private Role role;

    @Column
    private String reg_date;



    @Builder
    public User(String uid, String name, String password,String email, int birth,Role role,String reg_date){
        this.uid=uid;
        this.name=name;
        this.password=password;
        this.email=email;
        this.birth=birth;
        this.role = role;
        this.reg_date=reg_date;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return uid;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
