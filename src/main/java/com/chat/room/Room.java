package com.chat.room;

import javax.persistence.*;

@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @SequenceGenerator(
            name = "room_sequence",
            sequenceName = "room",
            allocationSize = 1
    )

    @GeneratedValue (
            strategy = GenerationType.SEQUENCE,
            generator = "room_sequence"
    )

    private String name;
    private Long id;
    public  Room() {}

    public Room (Long id, String name) {
        this.id = id;
        this.name = name;
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
}
