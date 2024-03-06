package com.project.task.service.impl;

import com.project.task.entity.Task;
import com.project.task.model.TaskDto;
import com.project.task.repository.TaskRepository;
import com.project.task.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public Task createTask(TaskDto taskDto) {
        try{
            if (Objects.nonNull(taskDto)) {
                Task task = new Task();
                task = modelMapper.map(taskDto, Task.class);
                taskRepository.save(task);
            }
        } catch (Exception exception) {
            log.error("An Exception occured while creating task: {}", exception);
        }
        return null;
    }

    @Override
    public Task updateTask(String taskId, Task task) {
        try{
            if (Objects.nonNull(taskId)) {
                Optional<Task> taskOptional = taskRepository.findTaskById(Long.valueOf(taskId));
                if (taskOptional.isPresent()) {
                    Task updateTask = taskOptional.get();
                    updateTask = modelMapper.map(task, Task.class);
                    updateTask.setId(Long.valueOf(taskId));
                    return taskRepository.save(updateTask);
                } else {
                    log.error("No task exist against task Id : {}", task.getId());
                    return null;
                }
            }
        } catch (Exception exception) {
            log.error("An Exception occured while updating task: {}", exception);
        }
        return null;
    }

    @Override
    public void deleteTask(String taskId) {
        try{
            if (Objects.nonNull(taskId)) {
                Optional<Task> taskOptional = taskRepository.findById(Long.valueOf(taskId));
                if (taskOptional.isPresent()) {
                    Task updateTask = taskOptional.get();
                     taskRepository.delete(updateTask);
                } else {
                    log.error("No task exist against task Id : {}", taskId);
                }
            }
        } catch (Exception exception) {
            log.error("An Exception occured while updating task: {}", exception);
        }
    }

    @Override
    public Task getTask(String taskId) {
        try{
        if (Objects.nonNull(taskId)) {
            Optional<Task> taskOptional = taskRepository.findTaskById(Long.getLong(taskId));
            if (taskOptional.isPresent()) {
                return taskOptional.get();
            } else {
                log.error("No task exist against task Id : {}", taskId);
                return null;
            }
        }
    } catch (Exception exception) {
        log.error("An Exception occured while Getting task: {}", exception);
    }
        return null;
    }

    @Override
    public List<Task> getAllTask() {
        return Objects.nonNull(taskRepository.findAll()) ?
                taskRepository.findAll().stream().sorted(Comparator.comparingLong(Task::getId))
                        .collect(Collectors.toList()) : new ArrayList<>();
    }
}
