package com.hyeon.book.springboot.domain.posts;

import com.hyeon.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor  //매개변수 없는 생성자(기본 생성자) 만들어줌
@Entity //클래스를 테이블 형태로 매칭해줌
public class Posts extends BaseTimeEntity {
    
    @Id //pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private Long id;
    
    @Column(length = 500, nullable = false)
    private String title;
    
    @Column(columnDefinition = "TEXT",nullable = false)
    private String content;
    
    private String author;
    
    @Builder
    public Posts(String title, String content, String author){
        this.title = title;
        this.content=content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title=title;
        this.content=content;
    }

}
