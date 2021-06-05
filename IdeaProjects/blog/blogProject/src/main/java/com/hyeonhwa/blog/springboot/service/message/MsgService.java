package com.hyeonhwa.blog.springboot.service.message;

import com.hyeonhwa.blog.springboot.domain.msg.Message;
import com.hyeonhwa.blog.springboot.domain.msg.MsgRepository;
import com.hyeonhwa.blog.springboot.domain.user.User;
import com.hyeonhwa.blog.springboot.web.dto.msg.MsgSaveRequestDto;
import com.hyeonhwa.blog.springboot.web.dto.posts.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MsgService {

    private final MsgRepository msgRepository;

    @Transactional
    public Long save(MsgSaveRequestDto msgSaveRequestDto,String sender) throws Exception{
        System.out.println("message save 실행");

        return msgRepository.save(msgSaveRequestDto.toEntity(sender)).getMsgNo();
    }

    @Transactional
    public List<Message> getMsgList(String uid){
        return msgRepository.getMsgList(uid);
    }

    @Transactional
    public List<Message> getSendMsgList(String uid){
        return msgRepository.getSendList(uid);
    }

    @Transactional
    public Message findByMsgNo(Long msgNo){
        return msgRepository.findByMsgNo(msgNo);
    }

    @Transactional
    public void delete(Long msgNo){
        Message msg = msgRepository.findByMsgNo(msgNo);
        msgRepository.delete(msg);
    }
}
