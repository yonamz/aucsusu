package com.hyeonhwa.blog.springboot.domain.msg;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MsgRepository extends JpaRepository<Message,Long> {
    @Query("SELECT m FROM Message m, User u WHERE (m.sender = u.uid) and (m.recipient=:uid) ORDER BY m.msgNo desc")
    List<Message> getMsgList(String uid);

    @Query("SELECT m FROM Message m, User u WHERE (m.sender = u.uid) and (m.sender=:uid) ORDER BY m.msgNo desc")
    List<Message> getSendList(String uid);
}
