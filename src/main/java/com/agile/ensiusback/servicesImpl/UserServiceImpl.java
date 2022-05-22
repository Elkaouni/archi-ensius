package com.agile.ensiusback.servicesImpl;

import com.agile.ensiusback.dao.Token;
import com.agile.ensiusback.dao.UserRepo;
import com.agile.ensiusback.entities.User;
import com.agile.ensiusback.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200/ensias/", allowedHeaders = "*", exposedHeaders = "Authorization")
@Service
@Transactional
@RestController
@RequestMapping("api/v1/")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Override
    @PostMapping("users")
    public User saveUser(@RequestBody User user) {
        user.setToken(new Token().nextString());
        User userN = userRepo.save(user);
        String sub = "Creation du compte";
        String html="<h1>Creation de votre compte</h1><p>Vous trouvez ci-joint les informations"
                + " necessaires pour vous connectez</p><ul><li>email : "+user.getEmail()+"</li><li>mot de passe : "
                +user.getPassword()+"</li></ul>";
        //SendEmail.sendMail(user.getEmail(),sub,html);
        return  userN;
    }

    @Override
    @PostMapping("login")
    public boolean login(@RequestBody String email, @RequestBody String pwd) {
        List<User> list = userRepo.findAll();
        for(User usr : list){
            if(usr.getEmail().equals(email) && usr.getPassword().equals(pwd)){
                return true;
            }
        }
        return false;
    }


    @GetMapping("users")
    public List<User> getUsers(){
        return this.userRepo.findAll();
    }

    @Override
    @GetMapping("users/email/{email}")
    public User findByEmail(@PathVariable String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    @GetMapping("users/userId/{userId}")
    public User findByUserId(@PathVariable Long userId) {
        return userRepo.findByUserId(userId);
    }

    @Override
    @GetMapping("users/username/{username}")
    public List<User> findByUsername(@PathVariable String username) {
        return userRepo.findByUsername(username);

    }

    @Override
    @GetMapping("users/token/{token}")
    public User findByToken(@PathVariable String token) {
        return userRepo.findByToken(token);
    }

    @Override
    @PutMapping("users/userId/{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody User user) {
        User userUpdated = userRepo.findByUserId(userId);
        userUpdated.setUsername(user.getUsername());
        userUpdated.setFirstName(user.getFirstName());
        userUpdated.setSecondName(user.getSecondName());
        userUpdated.setAppUserRole(user.getAppUserRole());
        userUpdated.setEnabled(user.getEnabled());
        userUpdated.setEmail(user.getEmail());
        userUpdated.setPassword(user.getPassword());
        userRepo.save(userUpdated);
        return userUpdated;
    }

    @Override
    @DeleteMapping("users/userId/{userId}")
    public boolean deleteUser(@PathVariable Long userId) {
        try{
            userRepo.deleteById(userId);
        }
        catch(Exception e){
            return false;
        }
        return true;
    }


}
