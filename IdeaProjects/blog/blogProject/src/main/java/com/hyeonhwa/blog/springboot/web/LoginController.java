package com.hyeonhwa.blog.springboot.web;

import com.hyeonhwa.blog.springboot.config.auth.dto.SessionUser;
import com.hyeonhwa.blog.springboot.domain.user.User;
import com.hyeonhwa.blog.springboot.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/login")
public class LoginController {

    private final UserService userService;

    @GetMapping(value = "/")
    public String loginForm(){
        return "login-form";
    }

    @GetMapping(value = "/signupForm")
    public String signupForm(){
        return "signupForm";
    }

    @PostMapping(value = "/user")
    public String login(HttpServletRequest req, Model model,String uid, String password){
        HttpSession session = req.getSession();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        User user = userService.login(uid,password);

        if(user == null){
            model.addAttribute("member",null);
            System.out.println("로그인 실패");
            return "redirect:/login/";
        }else{
            System.out.println("로그인 성공");
            model.addAttribute("member",user);
            session.setAttribute("member", user);
            return "redirect:/";
        }

    }


}
