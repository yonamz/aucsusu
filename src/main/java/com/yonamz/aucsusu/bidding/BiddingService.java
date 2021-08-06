package com.yonamz.aucsusu.bidding;

import com.yonamz.aucsusu.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BiddingService {
    private final BiddingRepository biddingRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public Long save(BiddingDto biddingDto){
        itemRepository.saveWinningBid(biddingDto.getBiddingPrice(), biddingDto.getUid(), biddingDto.getItemNo()); //item 테이블에 winning_bid 업데이트
        return biddingRepository.save(biddingDto.toEntity()).getBiddingId(); //bidding 테이블에 가격제안 저장
    }

    @Transactional
    public int findWinninBidByItemNo(Long itemNo){
        return itemRepository.findByItem_no(itemNo).getWinning_bid();
    }

    @Transactional
    public int findStartingBidByitemNo(Long itemNo) {
        return itemRepository.findByItem_no(itemNo).getStarting_bid();
    }

    @Transactional
    public List<BiddingDto> getBidding(String uid) {
        return biddingRepository.findBiddingByUid(uid);
    }
}