package com.codegym.service.approle;

import com.codegym.model.AppRole;
import com.codegym.repository.AppRoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppRoleService implements IAppRoleService {
    @Autowired
    private AppRoleRepo appRoleRepo;

    @Override
    public Iterable<AppRole> findAll() {
        return appRoleRepo.findAll();
    }

    @Override
    public Optional<AppRole> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(AppRole appRole) {

    }

    @Override
    public void delete(Long id) {

    }


}
