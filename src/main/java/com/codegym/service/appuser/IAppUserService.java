package com.codegym.service.appuser;

import com.codegym.model.AppUser;
import com.codegym.service.IGenerateService;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IAppUserService extends IGenerateService<AppUser>, UserDetailsService {
}
