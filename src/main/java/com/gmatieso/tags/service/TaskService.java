package com.gmatieso.tags.service;

import com.gmatieso.tags.dto.TaskDTO;
import com.gmatieso.tags.model.Tag;
import com.gmatieso.tags.model.Task;
import com.gmatieso.tags.repository.TagRepository;
import com.gmatieso.tags.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TagRepository tagRepository;

    public TaskDTO createTask(TaskDTO taskDTO) {
        logger.info("Attempting to create a task with title: {}", taskDTO.getTitle());

        // Check if a task with the same title already exists
        if (taskRepository.findAll().stream().anyMatch(task -> task.getTitle().equalsIgnoreCase(taskDTO.getTitle()))) {
            logger.error("Task creation failed: Task with title '{}' already exists.", taskDTO.getTitle());
            throw new TaskAlreadyExistsException("Task with title '" + taskDTO.getTitle() + "' already exists.");
        }

        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        task.setCompleted(taskDTO.isCompleted());
        task.setTags(getOrCreateTags(taskDTO.getTags()));

        Task savedTask = taskRepository.save(task);
        logger.info("Task created successfully with ID: {}", savedTask.getId());

        return mapToDTO(savedTask);
    }

    public List<TaskDTO> getAllTasks() {
        logger.info("Fetching all tasks from the database...");
        try {
            List<TaskDTO> tasks = taskRepository.findAll()
                    .stream()
                    .map(this::mapToDTO)
                    .collect(Collectors.toList());
            logger.info("Successfully fetched {} tasks.", tasks.size());
            return tasks;
        } catch (Exception ex) {
            logger.error("Error while fetching tasks: {}", ex.getMessage(), ex);
            throw ex;
        }
    }

    public TaskDTO updateTask(Long id, TaskDTO taskDTO) {
        logger.info("Attempting to update task with ID: {}", id);

        // Find the task by ID
        Optional<Task> existingTaskOpt = taskRepository.findById(id);
        if (existingTaskOpt.isEmpty()) {
            logger.error("Task update failed: Task with ID '{}' not found.", id);
            throw new ResourceNotFoundException("Task with id '" + id + "' not found.");
        }

        Task existingTask = existingTaskOpt.get();
        existingTask.setTitle(taskDTO.getTitle());
        existingTask.setCompleted(taskDTO.isCompleted());
        existingTask.setTags(getOrCreateTags(taskDTO.getTags()));

        Task updatedTask = taskRepository.save(existingTask);
        logger.info("Task with ID '{}' updated successfully.", id);

        return mapToDTO(updatedTask);
    }

    public String deleteTask(Long id) {
        logger.info("Attempting to delete task with ID: {}", id);

        // Check if the task exists before deleting
        Optional<Task> task = taskRepository.findById(id);
        if (task.isEmpty()) {
            logger.error("Task deletion failed: Task with ID '{}' not found.", id);
            throw new ResourceNotFoundException("Task with id '" + id + "' not found.");
        }

        taskRepository.deleteById(id);
        logger.info("Task with ID '{}' deleted successfully.", id);

        return "Task with id " + id + " deleted successfully.";
    }

    private Set<Tag> getOrCreateTags(Set<String> tagNames) {
        logger.info("Fetching or creating tags for names: {}", tagNames);
        return tagNames.stream().map(name -> {
            return tagRepository.findByName(name).orElseGet(() -> {
                logger.info("Creating new tag with name: {}", name);
                Tag tag = new Tag();
                tag.setName(name);
                return tagRepository.save(tag);
            });
        }).collect(Collectors.toSet());
    }

    private TaskDTO mapToDTO(Task task) {
        logger.info("Mapping task with ID '{}' to TaskDTO.", task.getId());
        TaskDTO dto = new TaskDTO();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setCompleted(task.isCompleted());
        dto.setTags(task.getTags().stream().map(Tag::getName).collect(Collectors.toSet()));
        return dto;
    }
}
