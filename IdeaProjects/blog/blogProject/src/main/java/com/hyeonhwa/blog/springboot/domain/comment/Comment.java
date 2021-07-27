package com.hyeonhwa.blog.springboot.domain.comment;

import com.hyeonhwa.blog.springboot.domain.posts.Posts;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    private String title;
    private String content;

    private String writer;


}
