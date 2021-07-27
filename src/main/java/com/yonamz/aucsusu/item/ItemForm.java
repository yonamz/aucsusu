package com.yonamz.aucsusu.item;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class ItemForm {

    private long item_no;
    private String writer;
    private String title;
    private String content;
    private Date deadline;
    private int starting_bid;
    private int winning_bid;
    private String bidder;
    private Timestamp reg_date;
    private String fileName;
    private boolean soldOut;



    public Item toEntity(String sessionUser){
        Item build = Item.builder()
                .item_no(item_no)
                .writer(sessionUser)
                .title(title)
                .content(content)
                .starting_bid(starting_bid)
                .winning_bid(winning_bid)
                .bidder(bidder)
                .deadline(deadline)
                .reg_date(reg_date)
                .fileName(fileName)
                .soldOut(soldOut)
                .build();
        return build;
    }

    @Builder
    public ItemForm(long item_no, String writer, String title, String content, Date deadline,
                    int starting_bid, int winning_bid, String bidder, Timestamp reg_date, String fileName, boolean soldOut) {
        this.item_no = item_no;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.deadline = deadline;
        this.starting_bid = starting_bid;
        this.winning_bid = winning_bid;
        this.bidder = bidder;
        this.reg_date = reg_date;
        this.fileName = fileName;
        this.soldOut = soldOut;
    }
}