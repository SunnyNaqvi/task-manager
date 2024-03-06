package com.project.task.service;


import com.project.task.entity.Task;
import com.project.task.model.TaskDto;

import java.util.List;

public interface TaskService {
    Task createTask(TaskDto taskDto);

    Task updateTask(String taskId, Task task);

    void deleteTask(String taskId);

    Task getTask(String taskId);

    List<Task> getAllTask();
}
