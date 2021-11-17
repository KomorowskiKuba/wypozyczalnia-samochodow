package com.project.carrental;

import com.project.carrental.models.Authority;
import com.project.carrental.models.User;
import com.project.carrental.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class CarRentalApplication {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UsersRepository usersRepository;

	public static void main(String[] args) {
		SpringApplication.run(CarRentalApplication.class, args);
	}

	@PostConstruct
	protected void init() {
		List<Authority> authorityList = new ArrayList<>();
		authorityList.add(new Authority("USER", "User role"));
		authorityList.add(new Authority("ADMIN", "Admin role"));

		User user = new User();

		user.setUserName("komo");
		user.setFirstName("Kuba");
		user.setLastName("Komorowski");

		user.setPassword(passwordEncoder.encode("1234"));
		user.setEnabled(true);

		usersRepository.save(user);
	}

	//private Authority createAuthority
}
