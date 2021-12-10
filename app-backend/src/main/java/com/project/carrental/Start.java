package com.project.carrental;

import com.project.carrental.models.ApplicationUser;
import com.project.carrental.repositories.ApplicationUsersRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Start {
    private ApplicationUsersRepository applicationUsersRepository;

    public Start(ApplicationUsersRepository applicationUsersRepository, PasswordEncoder passwordEncoder) {
        this.applicationUsersRepository = applicationUsersRepository;

        ApplicationUser applicationUserAdmin = new ApplicationUser("admin", passwordEncoder.encode("pass"), "ROLE_ADMIN");

        applicationUsersRepository.save(applicationUserAdmin);


        ApplicationUser applicationUser = new ApplicationUser("user", passwordEncoder.encode("pass"), "ROLE_USER");

        applicationUsersRepository.save(applicationUser);
    }
}
