package com.chat.registration;

import com.chat.user.User;
import com.chat.user.UserRepository;
import com.chat.user.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public RegistrationService (
            RegistrationRepository registrationRepository,
            UserRepository userRepository,
            BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.registrationRepository = registrationRepository;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    /**
     * Metodo para dar de alta nuevos usuarios
     * @param user
     * @return devuelve el usuario creado o una excepcion
     */
    public User register(User user) throws EmailTakenException, UsernameTakenException {

        // Comprobamos que no exista otro usuario con el mismo nombre
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            // El nombre de usuario esta en uso
            throw new UsernameTakenException("Este nombre de usuario ya esta en uso.");
        }

        // Aqui se comprueba si el email ya ha sido usado
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            // El correo ya esta en uso ...
            throw new EmailTakenException("Este correo ya esta en uso.");
        }

        // Ciframos el password
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        // Asignamos el rol por defecto
        user.setRole(UserRole.USER);

        // Guardamos el usuario
        return registrationRepository.save(user);
    }
}
