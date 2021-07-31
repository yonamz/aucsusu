package com.yonamz.aucsusu.chat;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class ChatRoomDTO {
    private String roomId;
    private String name;
    private Set<WebSocketSession> sessions = new HashSet<>();

    public static ChatRoomDTO create(String name,String roomId){
        ChatRoomDTO room = new ChatRoomDTO();


        //room.roomId= UUID.randomUUID().toString();
        room.roomId=roomId;
        room.name = name;
        return room;
    }
}
