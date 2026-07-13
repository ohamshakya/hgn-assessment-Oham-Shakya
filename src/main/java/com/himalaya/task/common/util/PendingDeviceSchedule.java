package com.himalaya.task.common.util;

import com.himalaya.task.common.enums.AlertStatus;
import com.himalaya.task.entity.Alert;
import com.himalaya.task.repo.AlertRepo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class PendingDeviceSchedule {

    private final AlertRepo alertRepo;

    public PendingDeviceSchedule(AlertRepo alertRepo) {
        this.alertRepo = alertRepo;
    }

    @Scheduled(fixedRate = 10000)
    public void escalate(){
        LocalDateTime timeLimit = LocalDateTime.now().minusMinutes(5);
        List<Alert> alertByAlertStatusAndCreatedAtBefore = alertRepo.findAlertByAlertStatusAndCreatedAtBefore(AlertStatus.PENDING, timeLimit);
        for(Alert alert : alertByAlertStatusAndCreatedAtBefore){
            System.out.println(alert.getAlertStatus() + " " + alert.getLatitude() + " " + alert.getLongitude());
            alert.setAlertStatus(AlertStatus.ESCALATED);
            alertRepo.save(alert);
        }
    }
}
