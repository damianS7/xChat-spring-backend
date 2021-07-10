package com.chat.room;

public class RoomMessageResponse {
    public Long roomId;
    public Long senderId;
    public String sender;
    public String message;

    public RoomMessageResponse (Long roomId, Long senderId, String sender, String message) {
        this.roomId = roomId;
        this.senderId = senderId;
        this.sender = sender;
        this.message = message;
    }
}
