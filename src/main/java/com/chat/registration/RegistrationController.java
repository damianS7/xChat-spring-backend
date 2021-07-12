package com.chat.registration;

import com.chat.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class RegistrationController {

    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController (RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    /**
     * Metodo para crear nuevos usuarios.
     * Al enviar una peticion POST a la URL api/users/
     * con un objeto JSON con los campos de usuario requeridos
     * la aplicacion creara un nuevo usuario y nos devolvera el mismo
     * objeto si ha tenido exito.
     * @param user
     * @return Creado el usuario devuelve null
     */
    @PostMapping(path = "/api/users/registration", consumes = "application/json")
    public UserRegistrationResponse create(@RequestBody User user) throws EmailTakenException, UsernameTakenException {
        return registrationService.register(user);
    }
}
