package com.yonamz.aucsusu.chat;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface MessageRepository extends JpaRepository<Message,String> {


    @Query("select m from Message m where (user1=:user1 and user2=:user2) or (user1=:user2 and user2=:user1)")
    Message existsByUser1AndUser2(@Param("user1") String user1, @Param("user2") String user2);

    Message findByUser1AndUser2(@Param("user1") String user1, @Param("user2") String user2);

    @Query("select m from Message m where user1=:uid")
    List<Message> findRoomByUid1(@Param("uid") String uid);

    @Query("select m from Message m where user2=:uid")
    List<Message> findRoomByUid2(@Param("uid") String uid);
/*
    Message findByUser1AndRoomId(@Param("user1") String user1, @Param("roomId") String roomId);

    Message findByUser2AndRoomId(@Param("user2") String user1, @Param("roomId") String roomId);*/

    Message findByRoomId(@Param("roomId") String roomId);
}
