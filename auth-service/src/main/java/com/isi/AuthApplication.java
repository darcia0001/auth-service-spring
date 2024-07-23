package com.isi;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.SpringApplication;
@SpringBootApplication
//@ComponentScan(basePackages = {"com.isi.configuration", "com.isi.service"})
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
