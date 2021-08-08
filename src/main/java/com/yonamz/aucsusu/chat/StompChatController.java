package com.yonamz.aucsusu.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;


@Controller
@RequiredArgsConstructor
public class StompChatController {
    //특정 브로커로 메시지 전달
    private final SimpMessagingTemplate template;


    @MessageMapping(value = "/chat/enter")
    public void enter(ChatMessageDTO message) throws IOException {

        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }

    @MessageMapping(value = "/chat/message")
    public void message(ChatMessageDTO message){
        saveFile(message.getWriter(),message.getMessage(),message.getRoomId());
        template.convertAndSend("/sub/chat/room/"+message.getRoomId(),message);
    }


    // 파일를 저장하는 함수
    private void saveFile(String user,String message,String roomId) {
        String msg = message;

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("~/spring/aucsusu/src/main/resources/static/chat/"+roomId+".txt", true), "UTF-8"))) {
            if(!msg.isBlank()) {//공백 입력시 채팅 이력에 저장되지 않게 함
                writer.write(user + ":" + msg);
                writer.newLine();
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }
}


