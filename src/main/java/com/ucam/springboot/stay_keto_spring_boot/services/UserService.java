package com.ucam.springboot.stay_keto_spring_boot.services;

import com.ucam.springboot.stay_keto_spring_boot.dto.HabitDTO;
import com.ucam.springboot.stay_keto_spring_boot.entities.User;
import com.ucam.springboot.stay_keto_spring_boot.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository repository) {
        this.userRepository = repository;
    }

    public User getUserById(Long id) { return  userRepository.findUserById(id);}

    public User saveUser(User user) { return userRepository.save(user);}

    public User updateGoalWeight(Long userId, Double targetWeight) {
        return userRepository.findById(userId).map(user -> {
            user.setTargetWeight(targetWeight);
            return user;
        }).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

}
