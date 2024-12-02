package com.gmatieso.tags.repository;

import com.gmatieso.tags.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {
}
