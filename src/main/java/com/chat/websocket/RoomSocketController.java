package com.chat.websocket;

import com.chat.room.RoomMessageRequest;
import com.chat.room.RoomMessageResponse;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class RoomSocketController {
    // Endpoint al cual enviar los mensajes
    @MessageMapping("/chat")
    // Canal de susbscripcion, todos aquellos suscritos a /room/message
    // recibiran los mensajes por medio de broadcast
    @SendTo("/room/message")
    public RoomMessageResponse send(@RequestBody RoomMessageRequest request) {
        //System.out.println("Recibido RoomMessageRequest "  + request);
        //System.out.println("Recibido RoomMessageRequest "  + request.sender);
        //System.out.println(request.sender + "dice: " + request.message);
        return new RoomMessageResponse(request.roomId, request.senderId, request.sender, request.message);
    }
}
