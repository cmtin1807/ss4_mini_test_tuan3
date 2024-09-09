package com.codegym.controller;

import com.codegym.model.AppRole;
import com.codegym.model.AppUser;
import com.codegym.service.approle.IAppRoleService;
import com.codegym.service.appuser.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;

import static com.codegym.model.ROLE.ROLE_USER;

@Controller
@RequestMapping
public class MainController {
    @Autowired
    private IAppUserService appUserService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IAppRoleService appRoleService;

    @GetMapping("/reg")
    public String login(Model model) {
        model.addAttribute("user", new AppUser());
        return "reg";
    }

    @PostMapping("/reg")
    public String register(AppUser user) {

        AppRole appRole = appRoleService.findByName(ROLE_USER.toString());
        user.setRoles(Collections.singleton(appRole));

        String newPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(newPassword);
        appUserService.save(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
