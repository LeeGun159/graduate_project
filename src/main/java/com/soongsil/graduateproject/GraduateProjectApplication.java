package com.soongsil.graduateproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GraduateProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraduateProjectApplication.class, args);
    }

}
