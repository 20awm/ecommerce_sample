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

@Service
public class AuthService {

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
        // Fetch customer by email
        Customer customer = customerRepository.findByEmail(email);
        if (customer == null) {
            // Handle case where customer doesn't exist
            // (e.g., throw an exception or return an error response)
        }
        // Extract customer name
        Long customerId = customer.getCustomerId();
        String name = customer.getName();
        String role = customer.getRole();

        // Generate JWT with name and email
        return jwtUtil.generateToken(String.valueOf(customerId), name, email, role);
    }

    public Customer getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }
}