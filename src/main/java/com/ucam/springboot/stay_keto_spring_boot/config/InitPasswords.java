package com.ucam.springboot.stay_keto_spring_boot.config;

import com.ucam.springboot.stay_keto_spring_boot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Profile("dev")  // 👉 Solo correrá si el perfil "dev" está activado
@Component
public class InitPasswords implements CommandLineRunner {

    private final UserRepository userRepository;

    @Value("${init.password.enabled:false}")
    private boolean enabled; // 👉 Flag para activarlo si tú quieres

    public InitPasswords(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        if (!enabled) {  // 👉 Para que NO corra en producción
            System.out.println("InitPasswords DESACTIVADO");
            return;
        }

        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String email = "alejandro@gmail.com";
        String plainPassword = "MiContra123";

        userRepository.findByEmail(email).ifPresentOrElse(user -> {
            String hashed = encoder.encode(plainPassword);
            user.setPassword(hashed);
            userRepository.save(user);
            System.out.println("Contraseña actualizada para: " + email);
        }, () -> {
            System.out.println("Usuario no encontrado: " + email);
        });
    }
}
