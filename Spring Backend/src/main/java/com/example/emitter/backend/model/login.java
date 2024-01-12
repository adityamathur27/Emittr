package com.example.emitter.backend.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
public class login {
    @NotNull(message = "Username is required")
    @NotBlank(message = "Username cannot be blank")
    private String username;
    @NotNull(message="Password is required")
    @NotBlank(message="Password cannot be empty")
    private String password;
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
