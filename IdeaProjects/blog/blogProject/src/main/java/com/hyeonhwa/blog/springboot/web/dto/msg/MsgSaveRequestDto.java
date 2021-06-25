package com.hyeonhwa.blog.springboot.web.dto.msg;

import com.hyeonhwa.blog.springboot.domain.msg.Message;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
public class MsgSaveRequestDto {

    String sender;
    String recipient;
    String title;
    String content;
    LocalDate sendDate;
    LocalTime sendTime;
    Boolean confirmRead;
    Boolean delMsg;

    @Builder
    public MsgSaveRequestDto(String sender,String recipient, String title, String content, LocalDate sendDate, LocalTime sendTime,Boolean confirmRead,Boolean delMsg){
        this.title = title;
        this.content = content;
        this.sender=sender;
        this.recipient=recipient;
        this.sendDate=sendDate;
        this.sendTime=sendTime;
        this.confirmRead=confirmRead;
        this.delMsg=delMsg;
    }

    public Message toEntity(String sender1){
        return Message.builder()
                .title(title)
                .content(content)
                .recipient(recipient)
                .sender(sender1)
                .sendDate(LocalDate.now())
                .sendTime(LocalTime.now())
                .confirmRead(false)
                .delMsg(false)
                .build();
    }
}
