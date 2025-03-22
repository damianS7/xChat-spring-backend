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

    private final RoomService roomService;

    @Autowired
    public RoomController (RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/list")
    // Devuelve una lista con todas las "rooms" disponibles
    public List<Room> list() {
        return roomService.getRooms();
    }

    // Endpoint al cual enviar los mensajes
    @MessageMapping("/chat")
    // Canal de susbscripcion, todos aquellos suscritos a /room/message
    // recibiran los mensajes por medio de broadcast
    @SendTo("/room/message")
    public RoomMessageResponse send(@RequestBody RoomMessageRequest request) {
        return new RoomMessageResponse(request.roomId, request.senderId, request.sender, request.message);
    }
}
