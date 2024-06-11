package com.isi.auth_service.controller;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class DataController {


    @GetMapping("/data")
    public String helloAdmin() {
        return "Hello Admin";
    }

}