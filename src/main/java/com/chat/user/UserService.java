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
     * @return devuelve el usuario creado o una excepcion
     */
    public User createUser(User user) throws EmailTakenException {
        // Aqui se comprueba si el email ya ha sido usado
        if (userRepository.findByEmail(user.getEmail()) != null) {
            // El correo ya esta en uso ...
            throw new EmailTakenException("Este correo ya esta en uso.");
        }
        // El correo no ha sido usado previamente, podemos dar de alta al usuario.
        return userRepository.save(user);
    }

}
