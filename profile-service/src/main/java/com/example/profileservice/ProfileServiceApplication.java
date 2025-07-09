package com.example.profileservice;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@Slf4j
@SpringBootApplication
public class ProfileServiceApplication {

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        System.setProperty("NEO4J_URI", dotenv.get("NEO4J_URI"));
        System.setProperty("NEO4J_USER_NAME", dotenv.get("NEO4J_USER_NAME"));
        System.setProperty("NEO4J_PASSWORD", dotenv.get("NEO4J_PASSWORD"));

        SpringApplication.run(ProfileServiceApplication.class, args);

    }

}
