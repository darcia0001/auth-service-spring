package com.isi.repository;

import com.isi.entity.Role;
import com.isi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);

}