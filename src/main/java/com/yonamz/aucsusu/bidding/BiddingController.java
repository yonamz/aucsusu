package com.yonamz.aucsusu.bidding;

import com.yonamz.aucsusu.item.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.PrintWriter;

@Controller
@RequiredArgsConstructor
public class BiddingController {

    private final BiddingService biddingService;
    private final ItemService itemService;

    @PostMapping("/bidding")
    public String bidding(BiddingDto biddingDto){
        int winningBid = biddingService.findWinninBidByItemNo(biddingDto.getItemNo());
        int startingBid = biddingService.findStartingBidByitemNo(biddingDto.getItemNo());
        //시작가와 현재가보다 높은 금액만 제안 가능
        if(biddingDto.getBiddingPrice() > winningBid & biddingDto.getBiddingPrice() >= startingBid)
            biddingService.save(biddingDto);
        else
            System.out.println("현재가보다 높은 금액만 제안 가능합니다.");
        //biddingService.update(itemNo, biddingDto);
        return "redirect:/items/"+biddingDto.getItemNo();
    }

    @PostMapping("/sell")
    public String sell(Long itemNo, boolean soldOut){
        itemService.setSoldOut(itemNo, soldOut);
        return "redirect:/items/"+itemNo;
    }

    @PostMapping("/sellCancel")
    public String sellCancel(Long itemNo, boolean soldOut){
        itemService.setSoldOut(itemNo, soldOut);
        return "redirect:/items/"+itemNo;
    }
}
