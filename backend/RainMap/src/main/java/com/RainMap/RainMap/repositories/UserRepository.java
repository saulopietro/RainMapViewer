package com.RainMap.RainMap.repositories;

import com.RainMap.RainMap.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM tb_user WHERE email = :email")
    User findByEmail(@Param(value = "email") String email);
}
