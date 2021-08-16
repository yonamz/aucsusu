package com.yonamz.aucsusu.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MessageService {

    private final MessageRepository messageRepository;


    @Transactional
    public Message exist(String user1, String user2){
        return messageRepository.existsByUser1AndUser2(user1,user2);
    }

    @Transactional
    public Message save(Message message){
        return messageRepository.save(message);
    }

/*    @Transactional
    public Message findByUser1User2(String user1, String user2){
        return messageRepository.findByUser1AndUser2(user1, user2);
    }*/

    @Transactional
    public List<Message> findRoomByUid1(String uid){
        return messageRepository.findRoomByUid1(uid);
    }
    @Transactional
    public List<Message> findRoomByUid2(String uid){
        return messageRepository.findRoomByUid2(uid);
    }

/*    @Transactional
    public Message findByUser1AndRoomId(String user1, String roomId){
        return messageRepository.findByUser1AndRoomId(user1,roomId);
    }

    @Transactional
    public Message findByUser2AndRoomId(String user2, String roomId){
        return messageRepository.findByUser2AndRoomId(user2,roomId);
    }*/

    @Transactional
    public Message findByRoomId(String roomId){
        return messageRepository.findByRoomId(roomId);
    }

    @Transactional
    public void enterChat(String roomId){
        messageRepository.enterChat(roomId);
    }

    @Transactional
    public void exitChat(String roomId){
        messageRepository.exitChat(roomId);
    }
}
