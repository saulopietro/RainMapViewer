package com.RainMap.RainMap.controllers;

import com.RainMap.RainMap.controllers.exceptions.PasswordIncorrectException;
import com.RainMap.RainMap.dto.AlertDTO;
import com.RainMap.RainMap.dto.UserDTO;
import com.RainMap.RainMap.models.Alert;
import com.RainMap.RainMap.services.AlertService;
import com.RainMap.RainMap.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping(value = "/alert")
public class AlertController {

    @Autowired
    private AlertService service;

    @PostMapping
    public ResponseEntity<AlertDTO> insert(@RequestBody AlertDTO dto) {
        AlertDTO body = service.insert(dto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                                .buildAndExpand(dto.getId())
                                        .toUri();

        return ResponseEntity.created(location).body(body);
    }

    @GetMapping
    public ResponseEntity<List<AlertDTO>> findAll(@RequestParam(required = false) String filtro) {
        List<AlertDTO> alerts = service.findAll(filtro);

        return ResponseEntity.ok().body(alerts);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
