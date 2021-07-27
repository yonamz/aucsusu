package com.yonamz.aucsusu.page;

import com.yonamz.aucsusu.File.Files;
import com.yonamz.aucsusu.File.FilesService;
import com.yonamz.aucsusu.item.Item;
import com.yonamz.aucsusu.item.ItemForm;
import com.yonamz.aucsusu.item.ItemRepository;
import com.yonamz.aucsusu.item.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class PageController {

    private final ItemService itemService;

    @Autowired
    FilesService filesService;

    @Autowired
    public PageController(ItemService itemService) {
        this.itemService = itemService;
    }


}
