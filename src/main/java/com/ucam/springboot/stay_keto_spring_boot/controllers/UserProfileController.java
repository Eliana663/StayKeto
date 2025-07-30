
package com.ucam.springboot.stay_keto_spring_boot.controllers;

import com.ucam.springboot.stay_keto_spring_boot.entities.User;
import com.ucam.springboot.stay_keto_spring_boot.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/users")
public class UserProfileController {

    private final UserRepository userRepository;

    @Autowired
    public UserProfileController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @PostMapping("/{id}/upload-photo")
    public ResponseEntity<String> uploadPhoto(@PathVariable Long id,
                                              @RequestParam("file") MultipartFile file) {
        try {
            System.out.println("Recibiendo archivo para user " + id + ": " + file.getOriginalFilename());
            String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path dirUploads = Paths.get("uploads");
            Files.createDirectories(dirUploads);
            Path filepath = dirUploads.resolve(filename);
            System.out.println("Guardando archivo en: " + filepath.toAbsolutePath());
            Files.write(filepath, file.getBytes());

            Optional<User> optionalUser = userRepository.findById(id);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                user.setProfilePhoto(filename);
                userRepository.save(user);

            } else {
                System.out.println("Usuario con id " + id + " no encontrado.");
            }

            return ResponseEntity.ok("Foto subida con éxito: " + filename);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al subir la foto");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        return userRepository.findById(id).map(user -> {
            user.setName(updatedUser.getName());
            user.setLastName(updatedUser.getLastName());
            if (updatedUser.getBirthDate() != null) {
                user.setBirthDate(updatedUser.getBirthDate());
            }
            user.setGender(updatedUser.getGender());
            user.setEmail(updatedUser.getEmail());
            user.setCurrentWeight(updatedUser.getCurrentWeight());
            user.setTargetWeight(updatedUser.getTargetWeight());
            user.setPregnant(updatedUser.isPregnant());
            user.setActivityLevel(updatedUser.getActivityLevel());
            return ResponseEntity.ok(userRepository.save(user));
        }).orElse(ResponseEntity.notFound().build());
    }
}