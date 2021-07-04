package com.chat.user;

import org.springframework.beans.factory.annotation.Autowired;
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
    public User update(User user) {

        return user;
    }

    //
    public User findUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    // Este metodo se usa para ...
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow( () -> {
            throw new UsernameNotFoundException("Este nombre no ha sido encontrado.");
        });
    }
}
