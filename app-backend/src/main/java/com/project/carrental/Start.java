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
        //TODO: FIX THIS SHIT - rekordu billingdetails nie ma w bazie wiec nie mozna go updateowac potem XDDDDDD
        applicationUser.setBillingDetails(new BillingDetails(1L, "1234", "234", "xdd", applicationUser));
        applicationUsersRepository.save(applicationUser);
    }
}
