package com.chat.room;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/rooms")
@CrossOrigin
public class RoomController {

	@GetMapping("/")
    public String index() {
        return "rooms response!";
    }
	
	@GetMapping("/myrooms")
    public String index2() {
        return "another response!";
    }
}
