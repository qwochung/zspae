package com.example.profileservice;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
