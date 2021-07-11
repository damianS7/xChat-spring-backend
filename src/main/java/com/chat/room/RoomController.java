package com.chat.room;

import com.chat.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("api/rooms")
public class RoomController {

    private final RoomChatService roomChatService;
    private final RoomService roomService;

    @Autowired
    public RoomController (RoomService roomService, RoomChatService roomChatService) {
        this.roomService = roomService;
        this.roomChatService = roomChatService;
    }

    @GetMapping("/list")
    // Devuelve una lista con todas las "rooms" disponibles
    public List<Room> list() {
        return roomService.getRooms();
    }

    @PostMapping(path="/join/{id}")
    // Entra en la sala indicada a traves del id
    public List<User> join(@RequestBody User user) {
        return roomChatService.joinRoom(user);
    }

    @PostMapping("/exit/{id}")
    // Sale de la sala indicada a traves del id
    public Room exit(@RequestBody User user) {
        return roomChatService.exitRoom(user);
    }

    // Endpoint al cual enviar los mensajes
    @MessageMapping("/chat")
    // Canal de susbscripcion, todos aquellos suscritos a /room/message
    // recibiran los mensajes por medio de broadcast
    @SendTo("/room/message")
    public RoomMessageResponse send(@RequestBody RoomMessageRequest request) {
        return new RoomMessageResponse(request.roomId, request.senderId, request.sender, request.message);
    }}
