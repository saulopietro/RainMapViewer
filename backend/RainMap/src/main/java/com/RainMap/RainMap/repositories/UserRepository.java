package com.RainMap.RainMap.repositories;

import com.RainMap.RainMap.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findOne(String email);
}
