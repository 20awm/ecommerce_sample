package dev.bootcamp.ecommerce.controller;

import dev.bootcamp.ecommerce.dto.LoginRequest;
import dev.bootcamp.ecommerce.dto.LoginResponse;
import dev.bootcamp.ecommerce.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String token = authService.login(loginRequest.getEmail(), loginRequest.getPassword());
        if (token != null) {
            return ResponseEntity.ok(new LoginResponse("200", "Login successful", token));
        } else {
            return ResponseEntity.status(401).body(new LoginResponse("401", "Invalid email or password", null));
        }
    }
}