package com.project.carrental.services;

import com.project.carrental.models.ApplicationUser;
import com.project.carrental.repositories.ApplicationUsersRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private ApplicationUsersRepository applicationUsersRepository;

    public UserDetailsServiceImpl(ApplicationUsersRepository applicationUsersRepository) {
        this.applicationUsersRepository = applicationUsersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return applicationUsersRepository.findByUsername(s);
    }
}
