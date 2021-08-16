package com.yonamz.aucsusu.home.controller;

import com.yonamz.aucsusu.File.Files;
import com.yonamz.aucsusu.File.FilesService;
import com.yonamz.aucsusu.item.ItemForm;
import com.yonamz.aucsusu.item.ItemService;
import com.yonamz.aucsusu.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final HttpSession httpSession;
    private final ItemService itemService;
    private final FilesService filesService;

    @RequestMapping(value = "/")
    public String index(Model model){
        User user = (User)httpSession.getAttribute("user");

            List<ItemForm> items = itemService.getItemList();
            List<Files> files = filesService.getFilesList();
            model.addAttribute("items", items);


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
        return "user/user-info";
    }

    @GetMapping(value = "/deleteForm")
    public String deleteForm(Model model){
        User user = (User) httpSession.getAttribute("user");
        model.addAttribute(user);
        return "user/delete-user";
    }

    /* @GetMapping("/faq")
    public String faq(){
        return "faq";
    }*/ 

    @GetMapping("/chat/go")
    public String goChat(){
        return "chat/rooms";
    }
}
