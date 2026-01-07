package com.example.social.media.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import lombok.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfig {

    @Bean
    public MongoClient mongoClient(
            @org.springframework.beans.factory.annotation.Value("${spring.data.mongodb.uri}")
            String uri
    ) {
        return MongoClients.create(uri);
    }


}
