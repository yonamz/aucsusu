package com.hyeonhwa.blog.springboot.domain.msg;

import com.hyeonhwa.blog.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long msgNo;

    @Column
    String sender;

    @Column
    String recipient;

    @Column
    String title;

    @Column
    String content;

    @Column
    LocalDate sendDate;

    LocalTime sendTime;

    @Builder
    public Message( String sender, String recipient, String title, String content, LocalDate sendDate, LocalTime sendTime){
        this.title = title;
        this.content = content;
        this.sender=sender;
        this.recipient=recipient;
        this.sendDate=sendDate;
        this.sendTime=sendTime;
    }

}
