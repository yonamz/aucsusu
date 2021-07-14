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
    public String register(ItemForm itemForm, @RequestParam MultipartFile files, RedirectAttributes redirectAttributes,HttpServletRequest rq) throws Exception {

        HttpSession session = rq.getSession();
        User user = (User) session.getAttribute("user");

        itemService.create(itemForm,user.getUid());

        Files file = new Files();

        String sourceFileName = file.getFileName();
        File destinationFile;
        String destinationFileName;
        String fileUrl = "C:/spring/oksusu/src/main/resources/static/images/";

        do{
            destinationFileName = RandomStringUtils.randomAlphanumeric(32);
            destinationFile = new File(fileUrl + destinationFileName);
        } while (destinationFile.exists());

        destinationFile.getParentFile().mkdirs();
        files.transferTo(destinationFile);

        file.setFileName(destinationFileName);
        file.setFileUrl(fileUrl);
        file.setItemNo(itemForm.getItem_no()); //여기가 문제인가?

        redirectAttributes.addFlashAttribute("file",file);

        return "redirect:/items";
    }

    @PostMapping("/items/files") //안된다.
    public String register(@RequestParam("file") Files file){
        /*try {
            filesService.save(file);
        } catch (NumberFormatException e){
            System.out.println("java.lang.NumberFormatException 발생");
        }*/
        return "redirect:/";
    }

    @GetMapping("/items")
    public String list(Model model){
        List<ItemForm> items = itemService.getItemList();
        model.addAttribute("items", items);
        return "items/itemsList";
    }

    @GetMapping("/items/{item_no}")
    public String detail(@PathVariable("item_no") Long item_no, HttpServletRequest hsrq, Model model){

        HttpSession session = hsrq.getSession();
        User user = (User)session.getAttribute("user");

        ItemForm itemForm = itemService.getPost(item_no, user.getUid());
        Files files = filesService.findByItemNo(item_no);
        model.addAttribute("itemForm",itemForm);
        model.addAttribute("file", files);
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