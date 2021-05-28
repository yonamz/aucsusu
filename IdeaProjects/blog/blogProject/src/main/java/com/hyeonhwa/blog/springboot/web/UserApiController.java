package com.hyeonhwa.blog.springboot.web;

import com.hyeonhwa.blog.springboot.domain.user.User;
import com.hyeonhwa.blog.springboot.service.user.UserService;
import com.hyeonhwa.blog.springboot.web.dto.UserSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/user")
public class UserApiController {

    private final UserService userService;

    @PostMapping(value = "/signup")
    public Long signup(@RequestBody UserSaveRequestDto userSaveRequestDto){
        return userService.save(userSaveRequestDto);
    }



}
