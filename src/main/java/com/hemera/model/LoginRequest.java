package com.hemera.model;

public class LoginRequest {
    private String username;
    private String password;
    // Constructor vacío (requerido por frameworks como Spring)
    public LoginRequest() {}

    // Getters y setters
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