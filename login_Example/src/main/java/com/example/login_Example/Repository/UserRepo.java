package com.example.login_Example.Repository;

import com.example.login_Example.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<Users,Long> {
    Optional<Users> findByLogin(String login);
}
