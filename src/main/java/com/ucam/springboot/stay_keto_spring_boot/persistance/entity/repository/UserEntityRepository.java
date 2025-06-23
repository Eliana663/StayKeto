package com.ucam.springboot.stay_keto_spring_boot.persistance.entity.repository;

import com.ucam.springboot.stay_keto_spring_boot.persistance.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserEntityRepository extends CrudRepository<UserEntity, Long> {

    Optional<UserEntity> findUserEntityByUsername(String username);

   }
