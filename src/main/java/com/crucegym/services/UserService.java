package com.crucegym.services;

import com.crucegym.dtos.UserRegistrationDTO;
import com.crucegym.entities.User;
import com.crucegym.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(UserRegistrationDTO registrationDTO) {
        // Validar que el username y el email no estén en uso
        if (userRepository.existsByUsername(registrationDTO.getUsername())) {
            throw new RuntimeException("El nombre de usuario ya está en uso");
        }
        if (userRepository.existsByEmail(registrationDTO.getEmail())) {
            throw new RuntimeException("El correo electrónico ya está en uso");
        }

        // Cifrar el password antes de guardarlo
        String encryptedPassword = passwordEncoder.encode(registrationDTO.getPassword());

        // Crear y guardar el usuario
        User newUser = new User(registrationDTO.getUsername(), encryptedPassword, registrationDTO.getEmail());
        return userRepository.save(newUser);
    }
}
