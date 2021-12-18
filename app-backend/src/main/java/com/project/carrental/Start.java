package com.project.carrental;

import com.project.carrental.models.ApplicationUser;
import com.project.carrental.models.BillingDetails;
import com.project.carrental.repositories.ApplicationUsersRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Start {
    private ApplicationUsersRepository applicationUsersRepository;

    public Start(ApplicationUsersRepository applicationUsersRepository, PasswordEncoder passwordEncoder) {
        this.applicationUsersRepository = applicationUsersRepository;

        ApplicationUser applicationUserAdmin = new ApplicationUser("Marek", "Jankowski", "1234@wp.pl", "admin", passwordEncoder.encode("pass"), "ROLE_ADMIN");

        applicationUsersRepository.save(applicationUserAdmin);


        ApplicationUser applicationUser = new ApplicationUser("Marcin", "Kozak","1234@wp.pl", "user", passwordEncoder.encode("pass"), "ROLE_USER");
        applicationUser.setBillingDetails(new BillingDetails());
        applicationUsersRepository.save(applicationUser);
    }
}
