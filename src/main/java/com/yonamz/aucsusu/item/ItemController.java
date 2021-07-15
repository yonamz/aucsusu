package com.yonamz.aucsusu.item;

import com.yonamz.aucsusu.File.Files;
import com.yonamz.aucsusu.File.FilesService;
import com.yonamz.aucsusu.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.apache.commons.lang3.RandomStringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ItemController {

    private final ItemService itemService;

    @Autowired
    FilesService filesService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping(value="/items/new")
    public String registerForm(){
        return "items/registerItemForm";
    }

    @PostMapping(value = "/items/new")
    public String register(ItemForm itemForm, @RequestParam List<MultipartFile> files, HttpServletRequest rq) throws Exception {

        HttpSession session = rq.getSession();
        User user = (User) session.getAttribute("user");

        Long itemNo = itemService.create(itemForm,user.getUid());

        for(MultipartFile multipartFile : files) {
            Files file = new Files();

            //String sourceFileName = file.getFileName();
            File destinationFile;
            String destinationFileName;
            String fileUrl = "C:/spring/aucsusu/src/main/resources/static/images/";

            do {
                destinationFileName = RandomStringUtils.randomAlphanumeric(32);
                destinationFile = new File(fileUrl + destinationFileName);
            } while (destinationFile.exists());

            destinationFile.getParentFile().mkdirs();
            multipartFile.transferTo(destinationFile);
            //files.transFerTo(destinationFile);

            file.setFileName(destinationFileName);
            file.setFileUrl(fileUrl);
            file.setItemNo(itemNo);

            filesService.save(file);
        }

        return "redirect:/items";
    }


    @GetMapping("/items")
    public String list(Model model){
        List<ItemForm> items = itemService.getItemList();
        List<Files> files = filesService.getFilesList();
        model.addAttribute("items", items);
        model.addAttribute("files", files);
        return "items/itemsList";
    }

    @GetMapping("/items/{item_no}")
    public String detail(@PathVariable("item_no") Long item_no, HttpServletRequest hsrq, Model model){

        HttpSession session = hsrq.getSession();
        User user = (User)session.getAttribute("user");

        ItemForm itemForm = itemService.getPost(item_no, user.getUid());
        //Files files = filesService.findByItemNo(item_no);
        List<Files> filesList = filesService.findAllByItemNo(item_no);

        model.addAttribute("itemForm",itemForm);
        model.addAttribute("filesList", filesList);
        return "items/detail";
    }

    @GetMapping("/items/edit/{item_no}")
    public String edit(@PathVariable("item_no") Long item_no, HttpServletRequest hsrq, Model model){
        HttpSession session = hsrq.getSession();
        User user = (User)session.getAttribute("user");
        ItemForm itemForm = itemService.getPost(item_no, user.getUid());

        model.addAttribute("itemForm",itemForm);
        return "items/update";
    }

    @RequestMapping(value = "/item/edit/{item_no}", method = RequestMethod.PUT)
    public String update(ItemForm itemForm, HttpServletRequest rq){
        HttpSession session = rq.getSession();
        User user = (User) session.getAttribute("user");
        itemService.create(itemForm,user.getUid());
        return "redirect:/";
    }

    @DeleteMapping("/items/{item_no}")
    public String delete(@PathVariable("item_no") Long item_no){
        itemService.deletePost(item_no);
        return "redirect:/";
    }


}