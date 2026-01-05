package com.example.social.media.Service;

import com.example.social.media.config.SecurityConfig;
import com.example.social.media.dto.RegisterRequest;
import com.example.social.media.model.User;
import com.example.social.media.repository.UserRepository;
import lombok.AllArgsConstructor;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private SecurityConfig securityConfig;
    private PasswordEncoder passwordEncoder;



    public User registerUser(RegisterRequest request) {


        userRepository.findByEmail(request.getEmail())
                .ifPresent(user -> {
                    throw new RuntimeException("Email already registered");
                });


        String encryptedPassword = passwordEncoder.encode(request.getPassword());

        User user = new User(null, request.getUsername(), encryptedPassword, request.getEmail(), "",null
       );



        return userRepository.save(user);

    }
}
