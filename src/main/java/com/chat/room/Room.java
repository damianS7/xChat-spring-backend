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
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nombre de la sala
    private String name;

    // Descripcion de la sala
    private String description;

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
}