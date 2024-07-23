package com.isi.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Long id;

    @Size(max = 255)
    @Column(name = "Email")
    private String email;

    @Size(max = 255)
    @Column(name = "Password")
    private String password;

    @Size(max = 255)
    @Column(name = "Role")
    private String role;
    //@ManyToMany(fetch = FetchType.EAGER)
    //private Set<Role> roles;

    public Long getId() {
        return id;
    }
}