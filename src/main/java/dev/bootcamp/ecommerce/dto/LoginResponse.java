package dev.bootcamp.ecommerce.dto;

public class LoginResponse {
    private String status;
    private String message;
    private String token;
    private String name; // Add this field
    private String email; // Add this field
    private String address; // Add this field

    public LoginResponse(String status, String message, String token, String name, String email, String address) {
        this.status = status;
        this.message = message;
        this.token = token;
        this.name = name;
        this.email = email;
        this.address = address;
    }

    // Getters and Setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}