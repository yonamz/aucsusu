package com.hyeonhwa.blog.springboot.web;

import com.hyeonhwa.blog.springboot.service.UserService;
import com.hyeonhwa.blog.springboot.web.dto.UserSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RequestMapping(value = "/user")
public class UserApiController {

    private final UserService userService;

    @GetMapping("/login")
    public String login(UserSaveRequestDto requestDto, HttpServletRequest req, HttpServletResponse response){
        HttpSession session = req.getSession();

        //User login = userService.login(requestDto);

        return "";
    }

}
