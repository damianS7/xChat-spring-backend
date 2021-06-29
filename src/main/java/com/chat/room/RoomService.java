package com.chat.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    // Se necesita cargar las rooms disponibles en la BD
    // al iniciar la aplicacion !!!
    // Hacerlo en la clase ChatApplicacion ?
    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    // Devuelve todas las salas que existen
    public List<Room> getRooms() {
        return roomRepository.findAll();
    }

    // Comprueba si la "room" indicada existe en la base de datos
    public boolean exists(Room room) {
        return roomRepository.existsById(room.getId());
    }
}
