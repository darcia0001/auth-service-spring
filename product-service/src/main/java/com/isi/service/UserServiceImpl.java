package com.isi.service;

import com.isi.controller.ResourceNotFoundException;
import com.isi.entity.User;
import com.isi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService , UserDetailsService {

    @Autowired
    private final UserRepository repository = null;

    @Override
    public User getUserByEmail(String email) {
        return  repository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User with email is not found."));

    }

    public UserDetails loadUserByUsername(String username) throws ResourceNotFoundException {
        User user = repository.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthorities(user));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
       List<String> list= new ArrayList<>() ;
       list.add(user.getRole());
        return list.stream()
                .map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());
    }

    public void save(User user){
        this.repository.save(user);

    }
}