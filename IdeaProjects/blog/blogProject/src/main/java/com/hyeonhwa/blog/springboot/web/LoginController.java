package com.hyeonhwa.blog.springboot.web;

import com.hyeonhwa.blog.springboot.config.auth.dto.SessionUser;
import com.hyeonhwa.blog.springboot.domain.user.User;
import com.hyeonhwa.blog.springboot.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String login(HttpServletRequest req, Model model, String uid, String password, RedirectAttributes rttr){
        HttpSession session = req.getSession();

        User user = userService.login(uid,password);

        if(user == null){
            session.setAttribute("member", null);
            rttr.addFlashAttribute("error",true);
            System.out.println("로그인 실패");
            return "redirect:/login/";
        }else{
            model.addAttribute("member",user);
            session.setAttribute("member", user);
            return "redirect:/";
        }

    }


}
