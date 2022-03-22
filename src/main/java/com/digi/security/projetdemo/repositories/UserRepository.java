package com.digi.security.projetdemo.repositories;

import com.digi.security.projetdemo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User where u.username = :name")
    Optional<User> findUserWithName(String name);

    User findByUsername(String name);
}
