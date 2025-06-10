package com.RainMap.RainMap.services;

import com.RainMap.RainMap.dto.AlertDTO;
import com.RainMap.RainMap.models.Address;
import com.RainMap.RainMap.models.Alert;
import com.RainMap.RainMap.repositories.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class AlertService {

    @Autowired
    private AlertRepository repository;

    public AlertDTO insert(AlertDTO dto) {
        String enderecoOriginal = dto.getAddress().getEndereco(); // endereço vindo da requisição
        int maxLength = 255;

        if (enderecoOriginal.length() > maxLength) {
            enderecoOriginal = enderecoOriginal.substring(0, maxLength);
        }

        Alert alert = new Alert(dto.getTipoOcorrencia(), dto.getUrgencia(), new Address(enderecoOriginal, dto.getAddress().getLatitude(), dto.getAddress().getLongitude()), dto.getData());

        repository.save(alert);

        return new AlertDTO(alert);
    }

    public List<AlertDTO> findAll(String filtro) {
        if ("7d".equals(filtro)) {
            Instant seteDiasAtras = Instant.now().minus(7, ChronoUnit.DAYS);
            Timestamp newTimeStamp = Timestamp.from(seteDiasAtras);

            List<Alert> alerts = repository.findByDataAfter(newTimeStamp);
            return alerts.stream().map(x -> new AlertDTO(x.getId(), x.getTipoOcorrencia(), x.getUrgencia(), x.getAddress(), x.getData())).toList();

        } else if ("24h".equals(filtro)) {
            Instant umDiasAtras = Instant.now().minus(1, ChronoUnit.DAYS);
            Timestamp newTimeStamp = Timestamp.from(umDiasAtras);

            List<Alert> alerts = repository.findByDataAfter(newTimeStamp);
            return alerts.stream().map(x -> new AlertDTO(x.getId(), x.getTipoOcorrencia(), x.getUrgencia(), x.getAddress(), x.getData())).toList();

        } else {

            List<Alert> alerts = repository.findAll();
            return alerts.stream().map(x -> new AlertDTO(x.getId(), x.getTipoOcorrencia(), x.getUrgencia(), x.getAddress(), x.getData())).toList();

        }

    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
