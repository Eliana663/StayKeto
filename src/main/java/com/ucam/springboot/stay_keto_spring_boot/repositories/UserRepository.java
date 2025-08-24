package com.ucam.springboot.stay_keto_spring_boot.repositories;

import com.ucam.springboot.stay_keto_spring_boot.entities.Habit;
import com.ucam.springboot.stay_keto_spring_boot.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {

    Optional<User> findByName(String nombre);

    User findUserById(Long id);

}
