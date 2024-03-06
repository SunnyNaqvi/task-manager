package com.project.task.repository;

import com.project.task.entity.Task;
import com.project.task.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findTaskById(final Long id);
    Optional<Task> findTaskByTaskName(final String taskName);
    Optional<Task> findTaskByStatus(final TaskStatus taskStatus);
}
