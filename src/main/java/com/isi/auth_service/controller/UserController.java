package com.isi.auth_service.controller;

import com.isi.auth_service.entity.User;
import com.isi.auth_service.service.UserService;
import configuration.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtTokenUtil= new JwtUtil() ;

    @PostMapping(value = "/authenticate")
    public ResponseEntity<String> authenticateUser(
            @Valid @RequestBody AuthenticationRequest request
    ) {
         String email= request.getEmail();

        System.out.println("----------_>email saisie"+ email);

        User user = userService.getUserByEmail(email);
        System.out.println("----------_>user"+ user);

        var password = user.getPassword() ;//CryptoUtils.decrypt(user.getPassword());

        if (user.getPassword().equals(password)) {
            // generate token
            var token = jwtTokenUtil.getToken(user);

            return ResponseEntity.ok(new AuthToken(token).toString());
        }

        return ResponseEntity.badRequest().build();
    }
}