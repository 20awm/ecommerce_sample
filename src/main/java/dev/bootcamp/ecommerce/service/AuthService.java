package dev.bootcamp.ecommerce.service;

import dev.bootcamp.ecommerce.model.Customer;
import dev.bootcamp.ecommerce.repository.CustomerRepository;
import dev.bootcamp.ecommerce.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Service
public class AuthService {

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    public String login(String email, String password) {
        // Authenticate user (existing code)
        Customer customer = customerRepository.findByEmail(email);
        if (customer == null || !isValidPassword(customer, password)) {
            // Handle invalid email or password
            // (e.g., return an error response with appropriate status code)
            return null; // Return null to indicate login failure
        }

        // Generate JWT with customer ID and role
        Long customerId = customer.getCustomerId();
        String name = customer.getName();
        String role = customer.getRole();
        return jwtUtil.generateToken(String.valueOf(customerId), name, email, role);
    }

    private boolean isValidPassword(Customer customer, String providedPassword) {
        // Compare provided password with stored hashed password
        String storedHashedPassword = customer.getPassword(); // Retrieve from DB
        return passwordEncoder.matches(providedPassword, storedHashedPassword);
    }

    public Customer getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }
}