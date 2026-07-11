package com.himalaya.task.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Groups {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String groupName;

    @OneToMany(mappedBy = "groups", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Member> member = new ArrayList<>();

    @OneToMany(mappedBy = "groups", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Orders> orders = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
