package com.yonamz.aucsusu.user;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/user")
public class UserApiController {

    private final UserService userService;

    @PostMapping(value = "/signup")
    public Long signup(@RequestBody UserSaveRequestDto userSaveRequestDto){
        System.out.println("user controller 실행");
        return userService.save(userSaveRequestDto);
    }

    @GetMapping(value = "/{uid}/exists")
    public boolean saveIDCheck(@PathVariable("uid") String uid){
        return userService.saveIDCheck(uid);
    }

    @GetMapping(value = "/exists/{email}")
    public boolean saveEmailCheck(@PathVariable("email") String email){
        return userService.saveEmailCheck(email);
    }

    @PostMapping(value = "/update/{uid}")
    public String update(@PathVariable String uid, @RequestBody UserUpdateDto updateDto){
        userService.update(uid, updateDto);

        return uid;
    }

    @PostMapping(value = "/validation/{cPwd}")
    public Boolean validation(@PathVariable String cPwd, HttpSession session){
        User user = (User) session.getAttribute("user");

        Boolean a;


        if(cPwd.equals(user.getPassword())){
            return true;
        }else{
            return false;
        }


    }

    @DeleteMapping(value = "/delete/{uid}")
    public String delete(@PathVariable String uid){
        System.out.println("deleteController");
        userService.delete(uid);
        return uid;
    }



}
