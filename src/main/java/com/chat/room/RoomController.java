package com.chat.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/rooms")
public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController (RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/list")
    public List<Room> list() {
        return roomService.getRooms();
    }

    @GetMapping("/join")
    public String join() {
        return "{id: 5, roomName:'asas', message:'successfully joined'}!";
    }

    @GetMapping("/exit")
    public String exit() {
        return "another response!";
    }
}
