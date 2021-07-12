package com.chat.room;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RoomMessageResponse {
    public Long roomId;
    public Long senderId;
    public String sender;
    public String message;
    public String sent_at;

    public RoomMessageResponse (Long roomId, Long senderId, String sender, String message) {
        this.roomId = roomId;
        this.senderId = senderId;
        this.sender = sender;
        this.message = message;
        this.sent_at = new SimpleDateFormat("hh:mm:ss").format(new Date());
    }
}
