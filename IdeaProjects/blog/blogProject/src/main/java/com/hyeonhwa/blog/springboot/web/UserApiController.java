package com.hyeonhwa.blog.springboot.web;

import com.hyeonhwa.blog.springboot.domain.user.User;
import com.hyeonhwa.blog.springboot.service.user.UserService;
import com.hyeonhwa.blog.springboot.web.dto.user.UserSaveRequestDto;
import com.hyeonhwa.blog.springboot.web.dto.user.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/user")
public class UserApiController {

    private final UserService userService;

    @PostMapping(value = "/signup")
    public Long signup(@RequestBody UserSaveRequestDto userSaveRequestDto){

        return userService.save(userSaveRequestDto);
    }

    @GetMapping(value = "/{uid}/exists")
    public ResponseEntity<Boolean> checkUidDuplicate(@PathVariable String uid){
        ResponseEntity<Boolean> a= ResponseEntity.ok(userService.checkUidDuplicate(uid));
        System.out.println(a);
        return a;
    }

    @ResponseBody
    @PostMapping(value = "/update/{uid}")
    public String update(@PathVariable String uid, @RequestBody UserUpdateDto requestDto){
        System.out.println("update 컨트롤러 실행");
        userService.update(uid, requestDto);
        return uid;
    }

    @PostMapping(value = "/validation/{cPwd}")
    public ResponseEntity<Boolean> validation(@PathVariable String cPwd, HttpSession session){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = (User)session.getAttribute("member"); //현재 유저

        System.out.println("validation 실행");

        ResponseEntity<Boolean> a = ResponseEntity.ok(encoder.matches(cPwd, user.getPassword()));
        System.out.println("a"+a);
        return a;
    }

}
