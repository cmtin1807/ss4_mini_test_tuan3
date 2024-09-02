package com.codegym.service.appuser;

import com.codegym.model.AppUser;
import com.codegym.model.UserPrinciple;
import com.codegym.repository.AppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserService implements IAppUserService {
    @Autowired
    private AppUserRepo appUserRepo;

    @Override
    public Iterable<AppUser> findAll() {
        return appUserRepo.findAll();
    }

    @Override
    public Optional<AppUser> findById(Long id) {
        return appUserRepo.findById(id);
    }

    @Override
    public void save(AppUser appUser) {
        appUserRepo.save(appUser);
    }


    @Override
    public void delete(Long id) {
        appUserRepo.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        tu name -> AppUser;
        Optional<AppUser> userOptional = appUserRepo.findByName(username);
        AppUser appUser = userOptional.get();
        if (appUser == null) {
            throw new UsernameNotFoundException("User not found");
        }
//        Chuyen AppUser -> UserDetails
        return UserPrinciple.build(appUser);
    }
}
