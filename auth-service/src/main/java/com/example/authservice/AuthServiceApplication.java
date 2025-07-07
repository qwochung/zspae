package com.example.authservice;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthServiceApplication {

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

        System.setProperty("DATABASE_URL", dotenv.get("DATABASE_URL"));
        System.setProperty("DATABASE_USER_NAME", dotenv.get("DATABASE_USER_NAME"));
        System.setProperty("DATABASE_PASSWORD", dotenv.get("DATABASE_PASSWORD"));
        System.setProperty("JWT_SIGNER_KEY", dotenv.get("JWT_SIGNER_KEY"));

        SpringApplication.run(AuthServiceApplication.class, args);
    }

}
