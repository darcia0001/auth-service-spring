package com.isi.auth_service.service;

import com.isi.auth_service.entity.User;
import com.isi.auth_service.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository repository = null;

    @Override
    public User getUserByEmail(String email) {
        return Optional.ofNullable( repository.findByEmail(email))
                .orElseThrow(() -> new RuntimeException("User with email is not found."));

    }

    public void save(User user){
        this.repository.save(user);

    }
}