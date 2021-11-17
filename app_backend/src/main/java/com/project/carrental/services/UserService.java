package com.project.carrental.services;


import com.project.carrental.models.User;
import com.project.carrental.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UsersRepository usersRepository;

    @Override
    public User loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = usersRepository.findByUserName(userName);

        if (user == null) {
            throw new UsernameNotFoundException("User " + userName + " not found.");
        }

        return user;
    }
}
