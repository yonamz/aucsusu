package com.hyeonhwa.blog.springboot.web;

import com.hyeonhwa.blog.springboot.service.user.UserService;
import com.hyeonhwa.blog.springboot.web.dto.user.UserSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    @GetMapping(value = "/{uid}/exists")
    public ResponseEntity<Boolean> checkUidDuplicate(@PathVariable String uid){
        ResponseEntity<Boolean> a= ResponseEntity.ok(userService.checkUidDuplicate(uid));
        System.out.println(a);
        return a;
    }


}
