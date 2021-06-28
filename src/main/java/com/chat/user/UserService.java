package com.chat.user;

import com.chat.room.Room;
import com.chat.room.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //
    public User findUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    /**
     * Metodo para dar de alta nuevos usuarios
     * @param user
     * @return
     */
    public User createUser(User user) throws IllegalStateException {
        // Aqui se comprueba si el email ya ha sido usado
        if (userRepository.findByEmail(user.getEmail()) != null) {
            // El correo ya esta en uso ...
            // No funciona aun
            throw new IllegalStateException("Este correo esta en uso.");
        }
        // El correo no ha sido usado previamente, usuario dado de alta.
        return userRepository.save(user);
    }

}
