package com.example.social.media.Controller;


import com.example.social.media.model.Comment;
import com.example.social.media.model.Post;
import com.example.social.media.model.User;
import com.example.social.media.repository.PostRepository;
import com.example.social.media.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/api/posts")
@AllArgsConstructor
@RestController
public class PostController {

    private PostRepository postRepository;
    private UserRepository userRepository;



    @PostMapping(consumes = "multipart/form-data")
    public Post createPost(
            @RequestParam("username") String username,
            @RequestParam("description") String description,
            @RequestParam("image") MultipartFile image
    ) throws IOException {

        String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
        Path path = Paths.get("uploads/"+ fileName);
        Files.write(path, image.getBytes());

        Post post = Post.builder()
                .username(username)
                .description(description)
                .imagePath("/uploads/" + fileName)
                .createdAt(LocalDateTime.now())
                .build();

        return postRepository.save(post);
    }



    @PostMapping("/{postId}/comments")
    public Post addComment(
            @PathVariable("postId") String postId,
            @RequestParam("username") String username,
            @RequestParam("comment") String text
    ) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        Comment comment = Comment.builder()
                .username(username)
                .text(text)
                .createdAt(LocalDateTime.now())
                .build();

        post.getComments().add(comment);
        return postRepository.save(post);
    }

    @GetMapping
    public List<Post> getAllPosts(HttpSession session) {

        if (session.getAttribute("LOGGED_IN_EMAIL") == null) {
            throw new RuntimeException("Please login first");
        }

        return postRepository.findAll();
    }





}
