package com.agile.ensiusback;

import com.agile.ensiusback.dao.Token;
import com.agile.ensiusback.entities.AppUserRole;
import com.agile.ensiusback.entities.User;
import com.agile.ensiusback.servicesImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class EnsiusBackApplication implements CommandLineRunner {
    @Autowired
    private UserServiceImpl userServiceImpl ;

    public static void main(String[] args) {
        SpringApplication.run(EnsiusBackApplication.class, args);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void run(String[] args) throws Exception {
        //admin
        userServiceImpl.saveUser(new User(null,"admin", new Token().nextString(),
                "admin", "admin", new Date("02/03/2001"),
                "admin@um5.ac.ma", "admin", AppUserRole.ADMIN, false, true));

        //user
        userServiceImpl.saveUser(new User(null,"user", new Token().nextString(),
                "user", "user", new Date("02/03/2001"),
                "user@um5.ac.ma", "user", AppUserRole.ADMIN, false, true));

        userServiceImpl.getUsers().forEach(System.out::println);

    }
}
