package com.project.task.model;

import lombok.Data;

@Data
public class TaskDto {
    private String taskName;
    private TaskStatus status;
    private String assignedTo;
}
