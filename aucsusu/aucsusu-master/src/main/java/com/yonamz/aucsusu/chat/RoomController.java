package com.yonamz.aucsusu.chat;

import com.yonamz.aucsusu.user.User;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping(value="/chat")
@Log4j2
public class RoomController {

    private final ChatRoomRepository repository;

    //채팅방 목록 조회
    @GetMapping(value = "/rooms")
    public ModelAndView rooms(){
        log.info("#All chat rooms");
        ModelAndView mv = new ModelAndView("chat/rooms");

        mv.addObject("list",repository.findAllRooms());

        return mv;
    }

    @GetMapping("/room/{writer}")
    public String create(RedirectAttributes rttr, @PathVariable("writer") String name,Model model, HttpServletRequest rq){
        log.info("#Create Chat Room, name : "+name);
        rttr.addFlashAttribute("roomName", repository.createChatRoomDTO(name));

        //System.out.println(repository.findRoomById(name));
        HttpSession session  = rq.getSession();
        User user = (User)session.getAttribute("user");
        log.info("#get chat Room, roomID : "+name);

        model.addAttribute("user",user);
        model.addAttribute("room",repository.findRoomById(name));
        return "chat/room";
    }

    /*//채팅방 조회
    @GetMapping("/room")
    public void getRoom(Model model, HttpServletRequest rq){
        HttpSession session  = rq.getSession();
        User user = (User)session.getAttribute("user");
        log.info("#get chat Room, roomID : "+name);

        model.addAttribute("user",user);
        model.addAttribute("room",repository.findRoomById(roomId));
    }*/
}
