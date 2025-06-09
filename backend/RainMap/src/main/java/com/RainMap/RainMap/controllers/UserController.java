package com.RainMap.RainMap.controllers;

import com.RainMap.RainMap.dto.UserDTO;
import com.RainMap.RainMap.models.User;
import com.RainMap.RainMap.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<UserDTO> insert(@RequestBody UserDTO dto) {
        UserDTO body = service.insert(dto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                                .buildAndExpand(dto.getId())
                                        .toUri();

        return ResponseEntity.created(location).body(body);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        List<UserDTO> users = service.findAll();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping
    public ResponseEntity<String> findOne(@RequestParam String email, String password) {
        UserDTO userDTO = service.findOne(email);

        if(userDTO.getPassword().matches(password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
        }
        
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Entrou");
    }
}
