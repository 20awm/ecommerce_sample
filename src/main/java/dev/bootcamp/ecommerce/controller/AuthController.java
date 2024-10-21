package dev.bootcamp.ecommerce.controller;

import dev.bootcamp.ecommerce.dto.LoginRequest;
import dev.bootcamp.ecommerce.dto.LoginResponse;
import dev.bootcamp.ecommerce.model.Customer;
import dev.bootcamp.ecommerce.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        String token = authService.login(loginRequest.getEmail(), loginRequest.getPassword());
        if (token != null) {
            return ResponseEntity.ok(new LoginResponse("200", "Login successful", token));
        } else {
            LoginResponse errorResponse = new LoginResponse("401", "Login failed", null);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }
    }
}