package com.gmatieso.tags.controller;

import com.gmatieso.tags.dto.TaskDTO;
import com.gmatieso.tags.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tasks")
public class TaskController {

    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private TaskService taskService;


    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO) {
        logger.info("Received request to create a task with title: {}", taskDTO.getTitle());
        try {
            TaskDTO createdTask = taskService.createTask(taskDTO);
            logger.info("Task created successfully with ID: {}", createdTask.getId());
            return ResponseEntity.ok(createdTask);
        } catch (Exception ex) {
            logger.error("Error creating task: {}", ex.getMessage(), ex);
            throw ex;
        }
    }


    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        logger.info("Received request to fetch all tasks.");
        try {
            List<TaskDTO> tasks = taskService.getAllTasks();
            logger.info("Fetched {} tasks successfully.", tasks.size());
            return ResponseEntity.ok(tasks);
        } catch (Exception ex) {
            logger.error("Error fetching tasks: {}", ex.getMessage(), ex);
            throw ex;
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
        logger.info("Received request to update task with ID: {}", id);
        try {
            TaskDTO updatedTask = taskService.updateTask(id, taskDTO);
            logger.info("Task with ID '{}' updated successfully.", id);
            return ResponseEntity.ok(updatedTask);
        } catch (Exception ex) {
            logger.error("Error updating task with ID '{}': {}", id, ex.getMessage(), ex);
            throw ex;
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        logger.info("Received request to delete task with ID: {}", id);
        try {
            String message = taskService.deleteTask(id);
            logger.info("Task with ID '{}' deleted successfully.", id);
            return ResponseEntity.ok(message);
        } catch (Exception ex) {
            logger.error("Error deleting task with ID '{}': {}", id, ex.getMessage(), ex);
            throw ex;
        }
    }
}
