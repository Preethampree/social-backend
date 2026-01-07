package com.example.social.media.Controller;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
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
import java.util.Map;

@RequestMapping("/api/posts")
@AllArgsConstructor
@RestController
public class PostController {

    private PostRepository postRepository;
    private UserRepository userRepository;
    private Cloudinary cloudinary;   // ✅ Cloudinary injected



    @PostMapping(consumes = "multipart/form-data")
    public Post createPost(
            @RequestParam String username,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) MultipartFile image
    ) throws IOException {

        String imageUrl = null;

        // ✅ Upload image ONLY if present
        if (image != null && !image.isEmpty()) {
            Map uploadResult = cloudinary.uploader().upload(
                    image.getBytes(),
                    ObjectUtils.emptyMap()
            );
            imageUrl = uploadResult.get("secure_url").toString();
        }

        Post post = Post.builder()
                .username(username)
                .description(description)   // can be null
                .imagePath(imageUrl)        // can be null
                .createdAt(LocalDateTime.now())
                .build();

        return postRepository.save(post);
    }




    @PostMapping("/{postId}/comments")
    public Post addComment(
            @PathVariable String postId,
            @RequestParam String username,
            @RequestParam String comment
    ) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        Comment newComment = Comment.builder()
                .username(username)
                .text(comment)
                .createdAt(LocalDateTime.now())
                .build();

        post.getComments().add(newComment);
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
