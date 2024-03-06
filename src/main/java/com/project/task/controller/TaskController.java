package com.project.task.controller;

import com.project.task.entity.Task;
import com.project.task.model.TaskDto;
import com.project.task.service.TaskService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/manager")
@CrossOrigin
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class TaskController {

    TaskService taskService;


    @PostMapping(value = "/create")
    public ResponseEntity createTask(@RequestBody TaskDto taskDto) {
        log.info("Inside Create Task Method with request body : {}", taskDto);
        return ResponseEntity.ok(taskService.createTask(taskDto));
    }

    @GetMapping(value = "/get/{taskId}")
    public ResponseEntity getTaskById(@PathVariable(value = "taskId") String taskId) {
        log.info("Inside Get Task By Id Method with request Id : {}", taskId);
        taskService.getTask(taskId);
        return ResponseEntity.ok(taskService.getTask(taskId));
    }

    @PutMapping(value = "/update/{taskId}")
    public ResponseEntity updateTask(@PathVariable(value = "taskId") String taskId, @RequestBody Task task) {
        log.info("Inside Get Task By Id Method with request Id : {}", taskId);
        return ResponseEntity.ok(taskService.updateTask(taskId, task));
    }

    @DeleteMapping(value = "/delete/{taskId}")
    public ResponseEntity deleteTask(@PathVariable(value = "taskId") String taskId) {
        log.info("Inside Get Task By Id Method with request Id : {}", taskId);
        taskService.deleteTask(taskId);
        return ResponseEntity.ok(taskId);
    }

    @GetMapping(value = "/get")
    public ResponseEntity getAllTask() {
        log.info("Inside Get All Task Method " );
        return ResponseEntity.ok(taskService.getAllTask());
    }
}
