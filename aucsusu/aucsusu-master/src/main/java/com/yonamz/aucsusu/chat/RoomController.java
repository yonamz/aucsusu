package com.yonamz.aucsusu.chat;

import com.yonamz.aucsusu.item.Item;
import com.yonamz.aucsusu.item.ItemForm;
import com.yonamz.aucsusu.item.ItemRepository;
import com.yonamz.aucsusu.item.ItemService;
import com.yonamz.aucsusu.user.User;
import com.yonamz.aucsusu.user.UserRepository;
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
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping(value="/chat")
@Log4j2
public class RoomController {

    private final ChatRoomRepository repository;
    private final MessageService messageService;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;


    //채팅방 목록 조회
    @GetMapping(value = "/rooms")
    public ModelAndView rooms(){
        log.info("#All chat rooms");
        ModelAndView mv = new ModelAndView("chat/rooms");

        mv.addObject("list",repository.findAllRooms());

        return mv;
    }



    @GetMapping("/room")
    public String create(RedirectAttributes rttr,
                         @RequestParam(value = "writer") String writer,
                         Model model, HttpServletRequest rq){
        HttpSession session  = rq.getSession();
        User user1 = (User)session.getAttribute("user");    //user1 = 내 계정
        User user2 = userRepository.findByUid(writer);          //user2 = 채팅 보낼 사람
        Message msg;

        String roomId;

        log.info("#Create Chat Room, name : "+writer);


        System.err.println("exist : "+messageService.exist(user1.getUid(),user2.getUid()));


        Message exist = messageService.exist(user1.getUid(),user2.getUid());

        if(exist==null){
            System.err.println("exist 실행");
            roomId = UUID.randomUUID().toString();
            rttr.addFlashAttribute("roomName", repository.createChatRoomDTO(writer,roomId));
            String name = writer+"와 "+user1.getUid()+"의 채팅";
            msg = new Message(roomId,name,user1.getUid(),user2.getUid());
            messageService.save(msg);
            System.err.println(roomId);
        }else{
            System.err.println("else 실행");

            if(exist.getUser1()!=user1.getUid()) {
                Message m = messageService.exist(user1.getUid(),user2.getUid());
                System.err.println("message : " + m.getRoomId());
                roomId = m.getRoomId();
                System.err.println("roomId : "+roomId);
                rttr.addFlashAttribute("roomName", repository.createChatRoomDTO(writer,roomId));
            }else{
                Message m = messageService.exist(user1.getUid(),user2.getUid());
                System.err.println("m : "+m);
                System.err.println("user2 : " + user2.getUid()+"user1 : "+user1.getUid());
                roomId = m.getRoomId();
                System.err.println("roomId : "+roomId);
                rttr.addFlashAttribute("roomName", repository.createChatRoomDTO(writer,roomId));
            }
        }


        model.addAttribute("room",repository.findRoomById(roomId));
        model.addAttribute("user",user1);
        return "chat/room";

    }

    @GetMapping("/croom")
    public String croom(RedirectAttributes rttr,
                         @RequestParam(value = "roomId") String roomId,
                         Model model, HttpServletRequest rq){
        HttpSession session  = rq.getSession();
        User sessionUser=(User)session.getAttribute("user");
        User user1;
        Message msg = messageService.findByRoomId(roomId);
        String writer;
        User user2;

        if(msg.getUser1()==sessionUser.getUid()) {
            writer = msg.getUser2();
            user2 = sessionUser;
            user1 = userRepository.findByUid(writer);          //user2 = 채팅 보낸사람
        }else{
            writer = msg.getUser1();
            user1 = sessionUser;
            user2 = userRepository.findByUid(writer);
        }


        Message exist = messageService.exist(user1.getUid(),user2.getUid());

        System.err.println("exist : "+exist);

        if(exist==null){

        }else{
            System.err.println("else 실행");
            Message m = messageService.exist(user1.getUid(),user2.getUid());

            System.err.println("message : "+m.getRoomId());
            roomId=m.getRoomId();

            System.err.println("roomId : "+roomId);
            rttr.addFlashAttribute("roomName", repository.createChatRoomDTO(writer,roomId));
        }
        ChatRoomDTO room=repository.findRoomById(roomId);
        //room.setName(msg.getName());
        model.addAttribute("room",repository.findRoomById(roomId));
        model.addAttribute("user",user1);
        return "chat/room";

    }

    @GetMapping("/myChat")
    public String myChat(Model model, HttpServletRequest rq){
        HttpSession session  = rq.getSession();
        User user = (User)session.getAttribute("user");
        model.addAttribute("user",user);



        model.addAttribute("list",messageService.findRoomByUid1(user.getUid()));
        model.addAttribute("message",messageService.findRoomByUid2(user.getUid()));
        //model.addAttribute("room",repository.findRoomById(roomId));
        return "chat/myChatList";
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
