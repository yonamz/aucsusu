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
public class MessageController {

    private final MsgService msgService;

    private final UserRepository userRepository;

    @GetMapping(value = "/writeMsg/{uid}")
    public String writeMsg(@PathVariable String uid, Model model){
        model.addAttribute("sender",userRepository.findByUid(uid));   //path=uid;
        return "/message/writeMsg";
    }

    @GetMapping(value = "/writeReMsg/{sender}")
    public String writeReMsg(@PathVariable String sender, Model model){
        model.addAttribute("sender", userRepository.findByUid(sender));
        return "/message/writeMsg";
    }

    @GetMapping(value = "/getMsgList")
    public String getMsgList(Model model, HttpServletRequest request, HttpSession session){
        System.out.println("getMsgList().....");

        User user = (User) session.getAttribute("member");
        String uid = user.getUid();
        model.addAttribute("msgList", msgService.getMsgList(uid));
        model.addAttribute("countMsg",msgService.countMsg(uid));
        model.addAttribute("readMsg",msgService.countReadMsg(uid));
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

    @GetMapping(value = "/msgView/{msgNo}")
    public String msgView(@PathVariable Long msgNo,Model model){
        model.addAttribute("msgDetail",msgService.findBysendMsgNo(msgNo));

        return "/message/msgView";
    }

    @GetMapping(value = "/sendView/{msgNo}")
    public String sendView(@PathVariable Long msgNo, Model model){
        model.addAttribute("msgDetail", msgService.findByMsgNo(msgNo));

        return "/message/sendView";
    }



}
