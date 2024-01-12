package com.example.emitter.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NotNull(message = "Name is required")
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotNull(message = "Email is required")
    @NotBlank(message = "Email cannot be blank")
    private String email;
    @NotNull(message = "Password is required")
    @NotBlank(message = "Password cannot be blank")
    private String password;
    @NotNull(message = "Username is required")
    @NotBlank(message = "Username cannot be blank")
    private String username;
    public Long getId() {
        return id;
    }

    public void setId(Long user_id) {
        this.id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String user_name) {
        this.name = user_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String user_email) {
        this.email = user_email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String user_password) {
        this.password = user_password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
