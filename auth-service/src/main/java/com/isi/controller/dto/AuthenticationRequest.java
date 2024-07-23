package com.isi.controller.dto;

public class AuthenticationRequest {

    public  AuthenticationRequest( String email, String password){
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
