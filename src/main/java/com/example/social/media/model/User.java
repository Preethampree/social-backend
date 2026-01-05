package com.example.social.media.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "Users")
public class User {

    @Id
    private String id;

    private String username;

    private String password;

    private String email;

    private String bio;

    private String profileImagePath;

}
