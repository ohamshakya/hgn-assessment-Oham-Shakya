package com.himalaya.task.repo;

import com.himalaya.task.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DeviceRepo extends JpaRepository<Device,Integer> {

    @Query("SELECT d FROM Device d WHERE d.deviceId = :deviceId")
    Optional<Device> findDeviceByDeviceId(String deviceId);
}
