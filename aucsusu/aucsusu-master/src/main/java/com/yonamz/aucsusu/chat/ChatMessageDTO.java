package com.yonamz.aucsusu.chat;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class ChatMessageDTO {
    private String roomId;
    private String writer;
    private String message;
}
