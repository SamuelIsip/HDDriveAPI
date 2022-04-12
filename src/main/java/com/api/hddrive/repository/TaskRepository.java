package com.api.hddrive.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.hddrive.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}
