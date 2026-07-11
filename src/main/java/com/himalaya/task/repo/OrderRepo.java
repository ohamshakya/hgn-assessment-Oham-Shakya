package com.himalaya.task.repo;

import com.himalaya.task.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Orders,Integer> {
}
