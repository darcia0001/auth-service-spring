package com.isi.service;

import com.isi.entity.User;

public interface UserService {

    User getUserByEmail(String email);
     void save(User user);

}
