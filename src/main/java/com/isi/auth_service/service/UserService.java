package com.isi.auth_service.service;

import com.isi.auth_service.entity.User;

public interface UserService {

    User getUserByEmail(String email);
}
