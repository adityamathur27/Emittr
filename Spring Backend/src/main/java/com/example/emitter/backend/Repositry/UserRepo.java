package com.example.emitter.backend.Repositry;

import com.example.emitter.backend.model.Registration;
import com.example.emitter.backend.model.login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Registration, Long> {
    Registration findByUsernameAndPassword(String username, String password);
    Registration findByUsernameOrEmail(String username, String email);

}

