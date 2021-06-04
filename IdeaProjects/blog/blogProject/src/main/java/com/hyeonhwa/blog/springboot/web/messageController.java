package com.hyeonhwa.blog.springboot.web;

import com.hyeonhwa.blog.springboot.domain.msg.Message;
import com.hyeonhwa.blog.springboot.domain.user.User;
import com.hyeonhwa.blog.springboot.domain.user.UserRepository;
import com.hyeonhwa.blog.springboot.service.message.MsgService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/message")
public class messageController {

    private final MsgService msgService;

    private final UserRepository userRepository;

    @GetMapping("/writeMsg/{uid}")
    public String writeMsg(@PathVariable String uid, Message msg, Model model){
        //request.setCharacterEncoding("UTF-8");
        model.addAttribute("sender",userRepository.findByUid(uid).get());
        return "/message/writeMsg";
    }


    @GetMapping(value = "/getMsgList")
    public String getMsgList(Model model, HttpServletRequest request, HttpSession session){
        System.out.println("getMsgList().....");

        User user = (User) session.getAttribute("member");
        String uid = user.getUid();
        model.addAttribute("msgList", msgService.getMsgList(uid));
        //model.addAttribute("allcnt",msgService.readMsgCnt(uid));
        //model.addAttribute("noReadMsg",msgService.confirmRead(uid));

        return "/message/msgList";

    }

    @GetMapping(value="/getSendList")
    public String getSendList(Model model,HttpServletRequest request,HttpSession session) throws Exception{
        System.out.println("getSendList()...");

        User user = (User)session.getAttribute("member");
        if(user!=null){
            String uid = user.getUid();
            model.addAttribute("sendList", msgService.getSendMsgList(uid));
        }else {
            System.out.println("user==null");
            return "/user/getUserList";
        }

        return "/message/sendList";
    }


}
