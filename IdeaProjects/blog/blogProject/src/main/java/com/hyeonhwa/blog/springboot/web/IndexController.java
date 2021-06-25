package com.hyeonhwa.blog.springboot.web;

import com.hyeonhwa.blog.springboot.config.auth.dto.SessionUser;
import com.hyeonhwa.blog.springboot.domain.user.User;
import com.hyeonhwa.blog.springboot.service.posts.PostsService;
import com.hyeonhwa.blog.springboot.service.user.UserService;
import com.hyeonhwa.blog.springboot.web.dto.posts.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final UserService userService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("posts", postsService.findAllDesc());
        User user = (User)httpSession.getAttribute("member");
        //SessionUser user1 = (SessionUser) httpSession.getAttribute("user");
        if(user!=null){
            model.addAttribute("member",user);
        }
        return "index";
    }

    @GetMapping(value = "/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        httpSession.invalidate();
        return "redirect:/";
    }


    @GetMapping(value="/posts/index")
    public String postsIndex(Model model){
        model.addAttribute("posts",postsService.findAllDesc());
        return "posts-index";
    }

    @GetMapping(value="/posts/save")
    public String postsSave(HttpSession session,Model model){
        model.addAttribute("author",session.getAttribute("member"));
        return "posts-save";
    }

    @GetMapping(value="/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){

        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post",dto);
        return "posts-update";
    }

    @GetMapping(value = "/user/getUserList")
    public String getUserList(Model model){
        model.addAttribute("userList",userService.findAllDesc());
        return "user-list";
    }

    @GetMapping(value = "/user/updateView")
    public String updateView(Model model, HttpSession session){
        model.addAttribute("user",session.getAttribute("member"));
        return "user-update";
    }

    @GetMapping(value = "/user/deleteView")
    public String deleteUser(Model model, HttpSession session){
        model.addAttribute("user", session.getAttribute("member"));
        return "user-delete";
    }
}
