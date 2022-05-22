package com.agile.ensiusback.services;

import com.agile.ensiusback.entities.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserService {

    User saveUser(User user);
    public boolean login( String email,String pwd);

    User findByEmail(String email);

    User findByUserId(Long idU);

    List<User> findByUsername(String username);

    User findByToken(String token);

    User updateUser(Long idU, User user);

    boolean deleteUser(Long idU);

}
