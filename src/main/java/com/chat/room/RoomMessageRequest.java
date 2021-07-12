package com.chat.room;

/**
 * Peticion usada para enviar mensajes en una sala
 */
public class RoomMessageRequest {
    // Id de la sala a la que se envia el mensaje
    public Long roomId;

    // El id del usuario que envia el mensaje
    public Long senderId;

    // El nombre del que envia el mensaje
    public String sender;

    // El mensaje a enviar
    public String message;

    public RoomMessageRequest() {

    }

    public RoomMessageRequest(Long roomId, Long senderId, String sender, String message) {
        this.roomId = roomId;
        this.senderId = senderId;
        this.sender = sender;
        this.message = message;
    }
}
