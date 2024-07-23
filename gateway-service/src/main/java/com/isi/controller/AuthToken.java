package com.isi.controller;

public class AuthToken {
    String token;
    public AuthToken(String token) {
        this.token=token;
    }


    @Override
    public String toString() {
        return  token;
    }
}
