package com.himalaya.task.repo;

import com.himalaya.task.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepo extends JpaRepository<Device,Integer> {
}
