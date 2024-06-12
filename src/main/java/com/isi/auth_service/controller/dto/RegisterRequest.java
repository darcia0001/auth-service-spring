package com.isi.auth_service.controller.dto;

public class RegisterRequest {

    public RegisterRequest(String email, String password){
        this.email=email;
        this.password= password;

    }


    String email;
    String password;

    public String getEmail(){
        return  this.email;
    }

    public String getPassword(){
        return this.password;
    }
}
