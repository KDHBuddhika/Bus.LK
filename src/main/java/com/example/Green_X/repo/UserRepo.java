package com.example.Green_X.repo;


import com.example.Green_X.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface UserRepo extends JpaRepository<User,Integer> {
    User findByEmailEquals(String email);

    Optional<User> findByEmail(String email);
}
