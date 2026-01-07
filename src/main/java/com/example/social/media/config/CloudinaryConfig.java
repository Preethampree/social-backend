package com.example.social.media.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(Map.of(
                "cloud_name", "daqbq2b1r",
                "api_key", "326478391137765",
                "api_secret", "zNjtn46SJxKNTKlTXWLrCJd2yqE"
        ));
    }

}
