package com.example.social.media.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterRequest {

    @NotBlank(message = "Username is Requried")
    private String username;
    @NotBlank(message = "Email is Requried")
    @Email(message = "Email Must Be Valid")
    private String email;

    @NotBlank(message = "password is requries")
    @Size(min = 6,message = "It Should Contain 6 characters")
    private String password;




}
