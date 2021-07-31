package com.yonamz.aucsusu.chat;

import com.yonamz.aucsusu.File.FilesService;
import com.yonamz.aucsusu.item.Item;
import com.yonamz.aucsusu.item.ItemForm;
import com.yonamz.aucsusu.item.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class StompChatController {
    //특정 브로커로 메시지 전달
    private final SimpMessagingTemplate template;


    @MessageMapping(value = "/chat/enter")
    public void enter(ChatMessageDTO message){

        message.setMessage(message.getWriter()+"님이 채팅방에 참여했습니다.");
        template.convertAndSend("/sub/chat/room/"+message.getRoomId(),message);
    }

    @MessageMapping(value = "/chat/message")
    public void message(ChatMessageDTO message){
        template.convertAndSend("/sub/chat/room/"+message.getRoomId(),message);
    }
}
