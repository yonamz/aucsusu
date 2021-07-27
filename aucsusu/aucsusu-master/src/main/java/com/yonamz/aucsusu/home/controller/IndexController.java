package com.yonamz.aucsusu.home.controller;

import com.yonamz.aucsusu.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final HttpSession httpSession;

    @RequestMapping(value = "/")
    public String index(Model model){
        User user = (User)httpSession.getAttribute("user");
        if(user!=null){
            model.addAttribute("user",user);
        }
        return "index";
    }

    @GetMapping(value = "/login")
    public String loginForm(){
        return "login/loginForm";
    }

    @GetMapping(value = "/signup")
    public String signupForm(){
        return "login/signupForm";
    }

    @GetMapping(value = "/logout")
    public String logout(){
        httpSession.invalidate();
        return "redirect:/";
    }

    @GetMapping(value = "/userInfo")
    public String userInfo(Model model){
        User user = (User) httpSession.getAttribute("user");
        model.addAttribute(user);
        return "user-info";
    }

    @GetMapping(value = "/deleteForm")
    public String deleteForm(Model model){
        User user = (User) httpSession.getAttribute("user");
        model.addAttribute(user);
        return "delete-user";
    }

    @GetMapping("/faq")
    public String faq(){
        return "faq";
    }
}
