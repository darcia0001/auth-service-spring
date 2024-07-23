package com.isi.controller;

import com.isi.controller.dto.AuthenticationRequest;
import com.isi.controller.dto.RegisterRequest;
 import com.isi.service.UserService;
import com.isi.service.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.isi.entity.User;
import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtTokenUtil;

    @PostMapping(value = "/auth/authenticate")
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

    @PostMapping(value = "/auth/register")
    public ResponseEntity<User> registerUser(
            @Valid @RequestBody RegisterRequest request
    ) {
        User user= new User();
        user.setRole("admin");
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
         userService.save(user);
        return ResponseEntity.ok(user);
    }
}