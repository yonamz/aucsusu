package com.yonamz.aucsusu.item;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/items")
public class ItemApiContoller {

    private final ItemService itemService;

    @PostMapping(value = "/{category}")
    public String digital(Model model, @PathVariable String category){
        List<ItemForm> items = itemService.getOneItemList("digital");
        model.addAttribute("items",items);
        return "items/itemsList";
    }
}
