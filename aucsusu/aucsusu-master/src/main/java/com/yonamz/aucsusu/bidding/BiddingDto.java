package com.yonamz.aucsusu.bidding;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BiddingDto {
    private Long biddingId;
    private Long itemNo;
    private String uid;
    private Integer biddingPrice;

    @Builder
    public BiddingDto(Long biddingId, Long itemNo, String uid, Integer biddingPrice){
        this.biddingId=biddingId;
        this.itemNo=itemNo;
        this.uid=uid;
        this.biddingPrice=biddingPrice;
    }

    public Bidding toEntity(){
        return Bidding.builder()
                .biddingId(biddingId)
                .itemNo(itemNo)
                .uid(uid)
                .biddingPrice(biddingPrice)
                .build();
    }

}
