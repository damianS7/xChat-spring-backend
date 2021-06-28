package com.chat.user;

import com.chat.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Metodo de prueba. BORRAR antes de desplegar la app.
    @GetMapping("/{id}")
    public User get(@PathVariable Long id) {
        return userService.findUser(id);
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
    @PostMapping(path = "/", consumes = "application/json")
    public User create(@RequestBody User user) throws Exception {
        return userService.createUser(user);
    }
}
