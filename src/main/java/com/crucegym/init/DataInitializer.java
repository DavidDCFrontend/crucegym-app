package com.crucegym.init;

import com.crucegym.entities.Role;
import com.crucegym.entities.User;
import com.crucegym.repositories.RoleRepository;
import com.crucegym.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        // Crear roles si no existen
        Role adminRole = roleRepository.findByRoleName("ADMIN")
                .orElseGet(() -> roleRepository.save(new Role("ADMIN")));
        Role userRole = roleRepository.findByRoleName("USER")
                .orElseGet(() -> roleRepository.save(new Role("USER")));

        // Crear usuario admin si no existe
        if(userRepository.findByUsername("admin").isEmpty()) {
            User adminUser = new User();
            adminUser.setUsername("admin");
            adminUser.setEmail("admincga@gmail.com");
            adminUser.setPassword(passwordEncoder.encode("1234"));
            adminUser.setRole(adminRole);  // Asignar el rol de admin al usuario

            // Guardar el usuario admin
            userRepository.save(adminUser);
        }
    }
}
