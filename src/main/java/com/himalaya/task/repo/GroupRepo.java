package com.himalaya.task.repo;

import com.himalaya.task.entity.Groups;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepo extends JpaRepository<Groups,Integer> {

    Groups findGroupsByGroupName(String groupName);
}
