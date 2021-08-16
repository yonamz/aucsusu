package com.yonamz.aucsusu.chat;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;


public interface MessageRepository extends JpaRepository<Message,String> {


    @Query("select m from Message m where (user1=:user1 and user2=:user2) or (user1=:user2 and user2=:user1)")
    Message existsByUser1AndUser2(@Param("user1") String user1, @Param("user2") String user2);

    @Query("select m from Message m where user1=:uid or user2=:uid")
    Message findByUser1AndUser2(@Param("uid") String uid);

    @Query("select m from Message m where user1=:uid")
    List<Message> findRoomByUid1(@Param("uid") String uid);

    @Query("select m from Message m where user2=:uid")
    List<Message> findRoomByUid2(@Param("uid") String uid);

    @Query("update Message m set m.deleted=true where user1=:uid or user2=:uid")
    @Transactional
    @Modifying
    void deletedChat(@Param("uid") String uid);

    @Query("update Message m set m.notify=true where roomId=:roomId")
    @Transactional
    @Modifying
    void enterChat(@Param("roomId") String roomId);

    @Query("update Message m set m.notify=false where roomId=:roomId")
    @Transactional
    @Modifying
    void exitChat(@Param("roomId") String roomId);

    Message findByRoomId(@Param("roomId") String roomId);
}
