package com.RainMap.RainMap.repositories;

import com.RainMap.RainMap.models.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

public interface AlertRepository extends JpaRepository<Alert, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM tb_alert WHERE data > :data")
    List<Alert> findByDataAfter(@Param("data") Timestamp data);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM tb_alert WHERE id = :id")
    void delete(@Param("id") Long id);
}
