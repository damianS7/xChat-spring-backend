package com.chat.room;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Transient;

import com.chat.user.User;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @SequenceGenerator(name = "room_sequence", sequenceName = "room", allocationSize = 1)
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "room_sequence")
    private Long id;

    // Nombre de la sala
    private String name;

    // Descripcion de la sala
    private String description;


    // Este campo NO se almacena en la BD
    // Este campo contiene la lista de usuarios que estan en la sala
    @Transient
    private final List<User> userList = new ArrayList<User>();

    public Room() {

    }

    public Room (Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Devuelve una lista con todos los usuarios que tiene la sala
    public List<User> getUserList() {
        return userList;
    }

    /**
     * Comprueba si el usuario ya esta dentro de la sala
     * @param user
     * @return true si el usuario ya esta en la sala
     */
    public boolean exists(User user) {
        return this.userList.contains(user);
    }

    // Agrega un nuevo usuario a la sala
    public void addUserToRoom(User user) {
        if (!this.exists(user)) {
            this.userList.add(user);
        }
    }
}