package com.hyeonhwa.blog.springboot.web;

import com.hyeonhwa.blog.springboot.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/user")
public class UserController {
    private final UserService userService;

    @DeleteMapping(value = "/delete/{uid}")
    public String deleteUser(@PathVariable String uid){
        userService.delete(uid);
        return uid;
    }
}
