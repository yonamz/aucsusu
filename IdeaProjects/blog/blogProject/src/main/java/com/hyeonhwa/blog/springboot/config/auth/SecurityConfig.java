package com.hyeonhwa.blog.springboot.config.auth;

import com.hyeonhwa.blog.springboot.domain.member.Role;
import com.hyeonhwa.blog.springboot.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().headers().frameOptions().disable()
                .and()
                .authorizeRequests().antMatchers("/","/login/**","/user/**","/posts/**","/layout/**","/css/**","/images/**","/js/**","/h2-console/**").permitAll()
                /*.antMatchers("/api/v1/**").hasRole(Role.USER.name())
                .anyRequest().authenticated()*/
                .and()
                .formLogin()
                .loginPage("/login/")
                .defaultSuccessUrl("/")
                .and()
                .logout().logoutSuccessUrl("/");
    }
}
