package com.himalaya.task.entity;

import com.himalaya.task.common.enums.Availability;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String deviceId;

    @OneToMany(mappedBy = "device")
    private List<AssignDevice> assignDevice = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Availability availability;
}
