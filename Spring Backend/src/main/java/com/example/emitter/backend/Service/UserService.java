package com.example.emitter.backend.Service;

import com.example.emitter.backend.Repositry.UserRepo;
import com.example.emitter.backend.model.Registration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    /**
     * Constructor based Dependency injection
     * @param userRepo
     */
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    /**
     * Save User to the database
     * @param username
     * @param password
     * @return
     */
    public Registration getUserbyusernamepassword(String username, String password){
        return userRepo.findByUsernameAndPassword(username,password);
    }
    public Registration registerUser(Registration user) {
        return userRepo.save(user);
    }
}
