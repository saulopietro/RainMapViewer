package com.RainMap.RainMap.services;

import com.RainMap.RainMap.dto.UserDTO;
import com.RainMap.RainMap.models.User;
import com.RainMap.RainMap.repositories.UserRepository;
import com.RainMap.RainMap.services.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public UserDTO insert(UserDTO dto) {

        User user = new User(dto.getName(), dto.getEmail(), dto.getPassword());

        repository.save(user);

        return new UserDTO(user);
    }

    public List<UserDTO> findAll() {

        return repository.findAll().stream().map(UserDTO::new).toList();
    }

    public UserDTO findOne(String email) {

        User user = repository.findByEmail(email);
        if (user != null) {
            return new UserDTO(user);
        }

        else throw new UserNotFoundException("Email não encontrado");
    }
}
