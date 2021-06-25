package com.hyeonhwa.blog.springboot.config.auth;

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
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().headers().frameOptions().disable()
                .and()
                .authorizeRequests().antMatchers("/","/login/**","/message/**","/user/**","/posts/**","/layout/**", "/static/css/**","/images/**","/js/**","/h2-console/**").permitAll()
                //.antMatchers("/api/v1/**").hasRole(Role.USER.name())
                //.anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login/")
                .defaultSuccessUrl("/")
                .and()
                .logout().logoutSuccessUrl("/").and().oauth2Login().userInfoEndpoint().userService(customOAuth2UserService);
    }
}
