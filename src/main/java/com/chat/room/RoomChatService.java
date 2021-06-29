package com.chat.room;

import com.chat.user.User;

import java.util.ArrayList;
import java.util.List;

// Logica referente a cada sala
public class RoomChatService {
    //private List<Room> liveRooms = new ArrayList<Room>();

    // Datos de la sala
    private Room room;
    // Contiene los usuarios que se encuentra actualmente en la sala
    private List<User> users = new ArrayList<User>();

    // Comprueba si el usuario ya esta en la sala
    public void existsInRoom() {

    }

    // El usuario entra a la sala
    public List<User> joinRoom(User user) {
        // Comprobamos que el usuario no este previamente en la sala
        users.add(user);
        // Devolvemos la lista con los usuarios de la sala
        return users;
    }

    // El usuario sale de la sala
    public Room exitRoom(User user) {
        // Comprobamos que el usuario este previamente en la sala
        users.remove(user);
        //return "{id: 5, roomName:'room A', message:'successfully exited'}!";
        return room;
    }
}
