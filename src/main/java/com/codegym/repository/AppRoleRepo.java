package com.codegym.repository;

import com.codegym.model.AppRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppRoleRepo extends CrudRepository<AppRole, Long> {

    Optional<AppRole> findByName(String name);
}
