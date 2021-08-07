package com.yonamz.aucsusu.bidding;

import com.yonamz.aucsusu.item.ItemForm;
import com.yonamz.aucsusu.item.ItemService;
import com.yonamz.aucsusu.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BiddingHistoryController {

    private final HttpSession httpSession;
    private final ItemService itemService;
    private final BiddingService biddingService;

    @GetMapping("/biddingHistory")
    public String biddingHistory(Model model){
        User user = (User) httpSession.getAttribute("user");
        String uid = user.getUid();
        List<ItemForm> biddingHistory = itemService.getBiddingHistory(uid);
        List<BiddingDto> biddingPrice = biddingService.getBidding(uid);
        model.addAttribute("user", uid);
        model.addAttribute("items", biddingHistory); //from item table
        model.addAttribute("biddings", biddingPrice); //from bidding table
        return "/items/biddingHistory";
    }
}