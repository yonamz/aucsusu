package com.yonamz.aucsusu.chat;


import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.io.*;

@Controller
@RequiredArgsConstructor
public class StompChatController {
    //특정 브로커로 메시지 전달
    private final SimpMessagingTemplate template;


    @MessageMapping(value = "/chat/enter")
    public void enter(ChatMessageDTO message) throws IOException {

        message.setMessage(message.getWriter()+"님이 입장했습니다");
        template.convertAndSend("/sub/chat/room/"+message.getRoomId(),message);
    }

    @MessageMapping(value = "/chat/message")
    public void message(ChatMessageDTO message){
        saveFile(message.getMessage());
        template.convertAndSend("/sub/chat/room/"+message.getRoomId(),message);
    }

    /*private String[] readFile() throws IOException {
        File file = new File("D:/aucsusu/aucsusu-master/src/main/resources/static/chat/test.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String rLine[] = new String[1000];
        int count=0;
        int nCount=0;
        String line="";
        String str="";
        while((line=bufferedReader.readLine())!=null){
            rLine[count] = line;
            count++;
        }

        *//*FileInputStream stream = new FileInputStream(file);
        return new String(stream.readAllBytes());*//*

        *//*try (FileInputStream stream = new FileInputStream(file)) {
            return new String(stream.readAllBytes());
        } catch (Throwable e) {
            e.printStackTrace();
            return "";
        }*//*
    }*/

    // 파일를 저장하는 함수
    private void saveFile(String message) {
        String msg = message;
// 파일을 저장한다.

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("D:/aucsusu/aucsusu-master/src/main/resources/static/chat/test.txt", true), "UTF-8"))) {
            writer.write(msg);
            writer.newLine();
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }
}
/*
@Controller
@RequiredArgsConstructor
public class StompChatController {


    //특정 브로커로 메시지 전달
    private final SimpMessagingTemplate template;

    @MessageMapping(value ="/chat/enter")
    public void enter(ChatMessageDTO message, Session userSession) {

        message.setMessage(message.getWriter() + "님이 채팅방에 참여했습니다.");

        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), readFile());
    }

    private String readFile() {
        File file = new File("D:/aucsusu/aucsusu-master/src/main/resources/static/chat/test.txt");

        if (!file.exists()) {
            return "";
        }
        try (FileInputStream stream = new FileInputStream(file)) {
            return new String(stream.readAllBytes());
        } catch (Throwable e) {
            e.printStackTrace();
            return "";
        }
    }

    // 파일를 저장하는 함수
    private void saveFile(String message) {
        String msg = message;
// 파일을 저장한다.

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("D:/aucsusu/aucsusu-master/src/main/resources/static/chat/test.txt", true), "UTF-8"))) {
            writer.write(msg);
            writer.newLine();
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }


    @MessageMapping( value = "/chat/message" )
    public void message(ChatMessageDTO message) {

            saveFile(message.getMessage());
            template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
        }
    }
*/

