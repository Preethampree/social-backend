package com.example.social.media.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Posts")
@Builder
public class Post {

    @Id
    private String id;

    private String username;          // who posted
    private String description;       // post text
    private String imagePath;         // image location

    private LocalDateTime createdAt;

    // comments list
    private ArrayList<Comment> comments = new ArrayList<>();


}
