package com.example.social.media.Controller;


import com.example.social.media.Service.UserService;
import com.example.social.media.dto.RegisterRequest;
import com.example.social.media.model.User;
import com.example.social.media.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;


    @PostMapping("/register")
    public String registeruser(@RequestBody RegisterRequest registerRequest){
        userService.registerUser(registerRequest);
        return "user Register Sucesufully";
    }

    @PostMapping("/{userId}/profile-photo")
    public User uploadProfilePhoto(
            @PathVariable String userId,
            @RequestParam MultipartFile image
    ) throws IOException {

        User user = userRepository.findByEmail(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String fileName = System.currentTimeMillis()  + "_" + image.getOriginalFilename();
        Path path = Paths.get( fileName);
        Files.write(path, image.getBytes());

        user.setProfileImagePath(fileName);
        return userRepository.save(user);
    }

}
