package com.himalaya.task.repo;

import com.himalaya.task.common.enums.AlertStatus;
import com.himalaya.task.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AlertRepo extends JpaRepository<Alert,Integer> {


    @Query("SELECT a FROM Alert a WHERE a.device.deviceId = :deviceId AND a.createdAt >= :timestamp")
    Optional<Alert> checkDeviceIdAndTimestamp(String deviceId, LocalDateTime timestamp);

    List<Alert> findAlertByAlertStatusAndCreatedAtBefore(
            AlertStatus status,
            LocalDateTime time);
}
