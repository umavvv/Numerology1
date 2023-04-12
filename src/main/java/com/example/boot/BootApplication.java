package com.example.boot;

import com.example.boot.model.Personality;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
        Initializer initializer = new Initializer();
        initializer.init();

    }
}