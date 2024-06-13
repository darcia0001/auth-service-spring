package com.isi.auth_service.controller;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class DataController {


    @GetMapping("/data3")
    public String helloAdmin() {
        return "Hello Admin";
    }
    @GetMapping("/auth")
    public String auth() {
        return "Hello from auth!";
    }

    @PostMapping("/auth")
    public String ff() {
        return "Hello from auth!";
    }

    @GetMapping("/get")
    public String handleGet() {
        return "Hello from the controller!";
    }

}