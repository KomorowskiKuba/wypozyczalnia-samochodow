package com.project.carrental.repositories;

import com.project.carrental.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface UsersRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);
}
