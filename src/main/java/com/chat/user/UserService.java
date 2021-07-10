package com.chat.user;

import com.chat.security.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Modifica los datos de un usuario
    public User update(UserUpdateRequest request) {
        // Obtenemos el id del usuario logeado que envia la peticion
        User currentUser = this.getCurrentUser();

        // Modificamos el usuario
        currentUser.setPassword(PasswordEncoder.encode(request.getPassword()));
        currentUser.setEmail(request.getEmail());

        // Guardamos los cambios
        userRepository.updateUser(currentUser.getId(), currentUser.getEmail(), currentUser.getPassword());
        return currentUser;
    }

    //
    public User findUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    // Obtenemos el id del usuario logeado que envia la peticion
    public User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    // Este metodo se usa para ...
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow( () -> {
            throw new UsernameNotFoundException("Este nombre no ha sido encontrado.");
        });
    }
}
