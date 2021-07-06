package com.yonamz.aucsusu.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/item")
public class ItemController {
    private final ItemService itemService;

    @RequestMapping("/all")
    public String getAll(){
        return "/item/item";
    }

    //상품등록 폼
    @RequestMapping("/addItem")
    public String additem(Model model){
        model.addAttribute("item",new Item());
        return "/item/addItem";
    }

    //상품등록
    @PostMapping("/register")
    public String registerItem(Item item, Model model) throws Exception {
        itemService.register(item);
        //model.addAttribute("msg","등록완료");
        return "item/all";
    }
}
