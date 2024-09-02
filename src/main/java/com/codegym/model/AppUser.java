package com.codegym.model;

import jakarta.persistence.*;
import lombok.Data;


import java.util.Set;

@Entity
@Data
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<AppRole> roles;
}
