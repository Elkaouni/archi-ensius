package com.agile.ensiusback.dao;

import com.agile.ensiusback.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {
    List<User> findAll();

    User findByUserId(Long userId);

    User findByToken(String token);

    List<User> findByUsername(String username);

    User findByEmail(String email);

}