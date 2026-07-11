package com.himalaya.task.repo;

import com.himalaya.task.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface AlertRepo extends JpaRepository<Alert,Integer> {

    Alert findAlertByUuidCode(String uuidCode);

    @Query("SELECT a FROM Alert a WHERE a.deviceId = :deviceId AND a.createdAt >= :timestamp")
    Optional<Alert> checkDeviceIdAndTImestamp(String deviceId, LocalDateTime timestamp);
}
