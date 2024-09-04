package com.codegym.config;


import com.codegym.service.appuser.AppUserService;
import com.codegym.service.appuser.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired
    private IAppUserService appUserService;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
        dao.setUserDetailsService(appUserService);
        dao.setPasswordEncoder(passwordEncoder());
        return dao;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .formLogin(Customizer.withDefaults())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/error_404", "/css/**", "/js/**", "/images/**","/reg").permitAll() // Các URL này đều được phép truy cập mà không cần đăng nhập
                        .requestMatchers("/posts/**","/i/**").hasAnyRole("USER", "ADMIN") // ROLE_USER và ROLE_ADMIN đều có quyền truy cập vào /posts/
                        .requestMatchers("/**").hasRole("ADMIN") // Chỉ ROLE_ADMIN mới có thể truy cập vào /types/
                        .anyRequest().authenticated() // Tất cả các request khác đều yêu cầu đăng nhập
                );
        return httpSecurity.build();
    }



    }
