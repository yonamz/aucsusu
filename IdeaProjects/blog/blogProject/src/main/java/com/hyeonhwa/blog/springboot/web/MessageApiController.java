package com.hyeonhwa.blog.springboot.web;

import com.hyeonhwa.blog.springboot.domain.msg.Message;
import com.hyeonhwa.blog.springboot.domain.user.User;
import com.hyeonhwa.blog.springboot.service.message.MsgService;
import com.hyeonhwa.blog.springboot.web.dto.msg.MsgSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/message")
public class MessageApiController {

    private final MsgService msgService;

    @PostMapping(value = "/sendMsg")
    public Long sendMsg(@RequestBody MsgSaveRequestDto msg, HttpSession session) throws Exception{
        User user = (User) session.getAttribute("member");
        System.out.println("현재 사용자 : "+user.getUid());
        String sender = user.getUid();
        return msgService.save(msg,sender);
    }

    @DeleteMapping(value = "/{msgNo}")
    public Long delete(@PathVariable Long msgNo){
        msgService.delete(msgNo);
        return msgNo;
    }
}
