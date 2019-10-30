package com.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
//@ComponentScan({"com.test.controller","com.test.service","com.test.db.dto"})

public class SpringBootFirstWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootFirstWebApplication.class, args);
    }
}