package com.yonamz.aucsusu.item;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Data
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="item_no")
    private Long itemNo;

    @Column(name = "title")
    private String title;

    @Column(name = "starting_bid")
    private Long startingBid;

    @Column(name = "deadline")
    private String deadline;

    @Column(name = "content")
    private String content;

    @Column(name = "writer")
    private String writer;

    @CreationTimestamp
    @Column(name = "reg_date")
    private Timestamp regDate;


}
