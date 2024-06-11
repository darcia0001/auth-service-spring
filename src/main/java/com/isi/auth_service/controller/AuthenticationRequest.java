package com.isi.auth_service.controller;

public class AuthenticationRequest {

    public  AuthenticationRequest( String email, String password){
        this.email=email;
        this.password= password;

    }


    String email;
    String password;

    String getEmail(){
        return  this.email;
    }

    String getPassword(){
        return this.password;
    }
}
