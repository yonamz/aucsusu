package com.yonamz.aucsusu.item;


import com.yonamz.aucsusu.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemHistoryController {

    private final HttpSession httpSession;
    private final ItemService itemService;

    @GetMapping("/myItemHistory")
    public String itemHistory(Model model){
        User user = (User) httpSession.getAttribute("user");
        String uid = user.getUid();
        List<ItemForm> itemHistory = itemService.getItemHistory(uid);
        model.addAttribute("seller", uid);
        model.addAttribute("items", itemHistory);
        return "/items/itemHistory";
    }

    @GetMapping("/userItemHistory/{uid}")
    public String itemHistory(@PathVariable("uid") String uid, Model model){
        List<ItemForm> itemHistory = itemService.getItemHistory(uid);
        model.addAttribute("seller", uid);
        model.addAttribute("items", itemHistory);
        return "/items/itemHistory";
    }
}