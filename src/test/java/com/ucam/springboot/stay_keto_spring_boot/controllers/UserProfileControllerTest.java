package com.ucam.springboot.stay_keto_spring_boot.controllers;

import com.ucam.springboot.stay_keto_spring_boot.entities.User;
import com.ucam.springboot.stay_keto_spring_boot.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class UserProfileControllerTest {

    private UserRepository userRepository;
    private UserProfileController controller;

    @BeforeEach
    void setUp() {

        userRepository = Mockito.mock(UserRepository.class);


        controller = new UserProfileController(userRepository);
    }

    @Test
    void testUpdateUser() {
        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setName("Eliana");
        mockUser.setEmail("eli@example.com");

              Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));
        Mockito.when(userRepository.save(any(User.class))).thenReturn(mockUser);

        User updatedUser = new User();
        updatedUser.setName("María");
        updatedUser.setEmail("maria@example.com");

        ResponseEntity<User> response = controller.updateUser(1L, updatedUser);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("María", response.getBody().getName());
        assertEquals("maria@example.com", response.getBody().getEmail());

        Mockito.verify(userRepository, Mockito.times(1)).save(mockUser);
    }
}
