package com.himalaya.task.entity;

import com.himalaya.task.common.enums.AlertStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Alert {

    @ManyToOne()
    @JoinColumn(name = "device_id")
    private Device device;

    private String longitude;

    @Column(unique = true)
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuidCode;

    private String latitude;

    private LocalDateTime timestamp;

    @Enumerated(EnumType.STRING)
    private AlertStatus alertStatus;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
