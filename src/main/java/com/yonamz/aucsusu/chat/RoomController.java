package com.yonamz.aucsusu.chat;

import com.yonamz.aucsusu.user.User;
import com.yonamz.aucsusu.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping(value="/chat")
@Log4j2
public class RoomController {

    private final ChatRoomRepository repository;
    private final MessageService messageService;
    private final UserRepository userRepository;


    @GetMapping("/room")
    public String create(RedirectAttributes rttr,
                         @RequestParam(value = "writer") String writer,
                         Model model, HttpServletRequest rq) throws IOException {
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
            session.setAttribute(roomId,roomId);
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

        boolean isExist=Files.exists(Path.of("~/spring/aucsusu/src/main/resources/static/chat/"+roomId+".txt"));
        if(isExist==true) {

            long lineCount = Files.lines(Path.of("~/spring/aucsusu/src/main/resources/static/chat/"+roomId+".txt")).count();

            System.err.println("user : " + user1);

            String[] message = new String[(int) lineCount];
            String[] auser = new String[(int) lineCount];

            for (int i = 0; i < lineCount; i++) {
                /*message.setMessage(message.getWriter()+"님이 입장했습니다");*/
                String totalMsg = readFile(roomId)[i];
                String storedUser = totalMsg.split(":")[0];

                message[i] = totalMsg.split(":")[1];
                auser[i] = storedUser;

                /*template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);*/
            }
            model.addAttribute("message", message);
            model.addAttribute("auser", auser);
            model.addAttribute("count", lineCount);
        }
        session.setAttribute("roomid",roomId);
        model.addAttribute("room",messageService.findByRoomId(roomId));
        model.addAttribute("user",user1);


        return "chat/room";

    }

    @GetMapping("/croom")
    public String croom(RedirectAttributes rttr,
                         @RequestParam(value = "roomId") String roomId,
                         Model model, HttpServletRequest rq) throws IOException {
        HttpSession session  = rq.getSession();
        User sessionUser=(User)session.getAttribute("user");
        User user1;
        Message msg = messageService.findByRoomId(roomId);
        String writer;
        User user2;

        if(msg.getUser1()==sessionUser.getUid()) {
            writer = msg.getUser2();
            user2 = sessionUser;
            user1 = userRepository.findByUid(writer);
        }else{
            writer = msg.getUser1();
            user1 = sessionUser;
            user2 = userRepository.findByUid(writer);
        }

        boolean isExist=Files.exists(Path.of("~/spring/aucsusu/src/main/resources/static/chat/"+roomId+".txt"));
        if(isExist==true) {

            long lineCount = Files.lines(Path.of("~/spring/aucsusu/src/main/resources/static/chat/"+roomId+".txt")).count();

            System.err.println("user : " + user1);

            String[] message = new String[(int) lineCount];
            String[] auser = new String[(int) lineCount];

            for (int i = 0; i < lineCount; i++) {
                String totalMsg = readFile(roomId)[i];
                String storedUser = totalMsg.split(":")[0];

                message[i] = totalMsg.split(":")[1];
                auser[i] = storedUser;

            }
            model.addAttribute("message", message);
            model.addAttribute("auser", auser);
            model.addAttribute("count", lineCount);
        }


        model.addAttribute("room",messageService.findByRoomId(roomId));
        model.addAttribute("user",user1);
        session.setAttribute("roomid",roomId);

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

    private String[] readFile(String roomId) throws IOException {
        File file = new File("~/spring/aucsusu/src/main/resources/static/chat/"+roomId+".txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String rLine[] = new String[bufferedReader.read()];

        int count=0;
        String line="";
        while((line=bufferedReader.readLine())!=null){
            rLine[count] = line;
            count++;
        }

        return rLine;

    }


}
