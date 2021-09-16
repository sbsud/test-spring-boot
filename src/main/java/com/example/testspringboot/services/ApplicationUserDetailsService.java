package com.example.testspringboot.services;

import com.example.testspringboot.model.ApplicationUser;
import com.example.testspringboot.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {

    @Autowired
    ApplicationUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<ApplicationUser> appUserOpt = appUserRepository.findById(username);
        if (appUserOpt.isPresent()) {
            ApplicationUser appUser = appUserOpt.get();
            return new User(appUser.getUsername(), appUser.getPassword(), Collections.emptyList());
        } else {
            throw new UsernameNotFoundException(username);
        }
    }
}
