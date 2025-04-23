package com.spring.techpractica.repository;

import com.spring.techpractica.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, Long> {




    Optional<User> findUserByUserEmail(String email);


    Optional<User> findUserByUserName(String username);
}