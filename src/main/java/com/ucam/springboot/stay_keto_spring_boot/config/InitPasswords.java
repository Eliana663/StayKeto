package com.ucam.springboot.stay_keto_spring_boot.config;

import com.ucam.springboot.stay_keto_spring_boot.entities.User;
import com.ucam.springboot.stay_keto_spring_boot.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Component
public class InitPasswords implements CommandLineRunner {

    private final UserRepository userRepository;

    public InitPasswords(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        PasswordEncoder encoder = new BCryptPasswordEncoder();

        // Email del usuario que quieres actualizar
        String email = "alejandro@gmail.com";

        // Contraseña que quieres establecer
        String plainPassword = "MiContra123";

        userRepository.findByEmail(email).ifPresentOrElse(user -> {
            // Genera el hash
            String hashedPassword = encoder.encode(plainPassword);
            user.setPassword(hashedPassword);

            // Guarda el usuario actualizado
            userRepository.save(user);

            System.out.println("Contraseña actualizada para: " + email);
        }, () -> {
            System.out.println("Usuario no encontrado: " + email);
        });
    }
}
