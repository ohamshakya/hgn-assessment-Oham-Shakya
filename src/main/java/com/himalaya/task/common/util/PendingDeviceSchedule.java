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
    public void sayHello(){
        LocalDateTime timeLimit = LocalDateTime.now().minusMinutes(5);
        List<Alert> alertByAlertStatusAndCreatedAtBefore = alertRepo.findAlertByAlertStatusAndCreatedAtBefore(AlertStatus.PENDING, timeLimit);
        for(Alert i : alertByAlertStatusAndCreatedAtBefore){
            System.out.println(i.getAlertStatus() + " " + i.getLatitude() + " " + i.getLongitude());
            i.setAlertStatus(AlertStatus.ESCALATED);
            alertRepo.save(i);
        }
    }
}
