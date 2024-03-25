package com.example.demo.Repositories;

import com.example.demo.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository <UserModel, Long> {
    Optional<UserModel> findByUsername(String username);

    @Query("SELECT u FROM UserModel u WHERE u.username = :username")
    String findUsernameIfExists1(@Param("username") String username);
}
