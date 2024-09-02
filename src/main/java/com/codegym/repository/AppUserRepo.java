package com.codegym.repository;

import com.codegym.model.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepo extends CrudRepository<AppUser, Long> {
    Optional<AppUser> findByName(String name);
}
