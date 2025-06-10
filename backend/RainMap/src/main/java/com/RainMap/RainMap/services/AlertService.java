package com.RainMap.RainMap.services;

import com.RainMap.RainMap.dto.AlertDTO;
import com.RainMap.RainMap.models.Alert;
import com.RainMap.RainMap.repositories.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlertService {

    @Autowired
    private AlertRepository repository;

    public AlertDTO insert(AlertDTO dto) {

        Alert alert = new Alert(dto.getTipoOcorrencia(), dto.getUrgencia(), dto.getAddress(), dto.getData());

        repository.save(alert);

        return new AlertDTO(alert);
    }

}
