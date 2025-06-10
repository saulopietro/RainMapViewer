package com.RainMap.RainMap.repositories;

import com.RainMap.RainMap.models.Alert;
import com.RainMap.RainMap.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertRepository extends JpaRepository<Alert, Long> {

}
