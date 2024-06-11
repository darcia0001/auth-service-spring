package com.isi.auth_service.service;

import com.isi.auth_service.entity.User;
import com.isi.auth_service.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public User getUserByEmail(String email) {
        Long id= 1L;
        //todo:  check why didnt link  to database   repository.findByEmail(email);
        Optional<User> u = repository.findById(id);
        System.out.println("----------_>usuer in service found"+  u );
        User user =  new User();
        user.setEmail("darcia.khoussa@gmail.com");
        user.setPassword("passer");
        user.setId(1L);
        user.setRole("admin");


        return Optional.ofNullable( user)
                .orElseThrow(() -> new RuntimeException("User with email is not found."));

    }
}