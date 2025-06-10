package com.RainMap.RainMap.repositories;

import com.RainMap.RainMap.models.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface AlertRepository extends JpaRepository<Alert, Long> {

    List<Alert> findByDataAfter(Timestamp data);

}
