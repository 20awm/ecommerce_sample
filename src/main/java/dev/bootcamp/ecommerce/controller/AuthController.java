package dev.bootcamp.ecommerce.controller;

import dev.bootcamp.ecommerce.dto.LoginRequest;
import dev.bootcamp.ecommerce.dto.LoginResponse;
import dev.bootcamp.ecommerce.model.Customer;
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
            Customer customer = authService.getCustomerByEmail(loginRequest.getEmail());
            String name = customer.getName();
            String email = customer.getEmail();
            String address = customer.getAddress();

            return ResponseEntity.ok(new LoginResponse("200", "Login successful", token, name, email, address));
        } else {
            return ResponseEntity.status(401).body(new LoginResponse("401", "Invalid email or password", null, null, null, null));
        }
    }
}