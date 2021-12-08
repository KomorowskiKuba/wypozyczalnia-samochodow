package com.project.carrental.services;

import com.project.carrental.models.ApplicationUser;
import com.project.carrental.repositories.ApplicationUsersRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private ApplicationUsersRepository applicationUsersRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(ApplicationUsersRepository applicationUsersRepository, PasswordEncoder passwordEncoder) {
        this.applicationUsersRepository = applicationUsersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void addUser(ApplicationUser applicationUser) {
        applicationUser.setPassword(passwordEncoder.encode(applicationUser.getPassword()));
        applicationUser.setRole("ROLE_USER");

        applicationUsersRepository.save(applicationUser);
        applicationUsersRepository.save(applicationUser);

    }
}
