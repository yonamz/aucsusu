package com.yonamz.aucsusu.chat;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Message {

    @Builder
    public Message(String roomId,String name,String user1, String user2){
        this.roomId=roomId;
        this.name = name;
        this.user1=user1;
        this.user2=user2;
    }

    @Id
    @Column(name = "roomId")
    private String roomId;

    @Column(name = "user1")
    private String user1;

    @Column(name = "user2")
    private String user2;

    @Column(name = "name")
    private String name;

    @Column(name = "deleted")
    @ColumnDefault("false")
    private boolean deleted;

    public Message toEntity(){
        return Message.builder()
                .roomId(roomId)
                .user1(user1)
                .user2(user2)
                .name(name)
                .build();
    }




}
