package com.yonamz.aucsusu.bidding;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Bidding {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long biddingId;

    @Column
    private Long itemNo;

    @Column
    private String uid;

    @Column
    @ColumnDefault("0")
    private Integer biddingPrice;

    @Builder
    public Bidding(Long biddingId, Long itemNo, String uid, Integer biddingPrice){
        this.biddingId = biddingId;
        this.itemNo = itemNo;
        this.uid = uid;
        this.biddingPrice = biddingPrice;
    }
}
