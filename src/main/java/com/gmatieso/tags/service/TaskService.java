package com.gmatieso.tags.service;

import com.gmatieso.tags.dto.TaskDTO;
import com.gmatieso.tags.model.Tag;
import com.gmatieso.tags.model.Task;
import com.gmatieso.tags.repository.TagRepository;
import com.gmatieso.tags.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TagRepository tagRepository;

    public TaskDTO createTask(TaskDTO taskDTO) {
        // Check if a task with the same title already exists
        if (taskRepository.findAll().stream().anyMatch(task -> task.getTitle().equalsIgnoreCase(taskDTO.getTitle()))) {
            throw new TaskAlreadyExistsException("Task with title '" + taskDTO.getTitle() + "' already exists.");
        }

        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        task.setCompleted(taskDTO.isCompleted());
        task.setTags(getOrCreateTags(taskDTO.getTags()));

        Task savedTask = taskRepository.save(task);
        return mapToDTO(savedTask);
    }

    public List<TaskDTO> getAllTasks() {
        return taskRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public TaskDTO updateTask(Long id, TaskDTO taskDTO) {
        // Find the task by ID
        Optional<Task> existingTaskOpt = taskRepository.findById(id);
        if (existingTaskOpt.isEmpty()) {
            throw new ResourceNotFoundException("Task with id '" + id + "' not found.");
        }

        Task existingTask = existingTaskOpt.get();
        existingTask.setTitle(taskDTO.getTitle());
        existingTask.setCompleted(taskDTO.isCompleted());
        existingTask.setTags(getOrCreateTags(taskDTO.getTags()));

        Task updatedTask = taskRepository.save(existingTask);
        return mapToDTO(updatedTask);
    }

    public String deleteTask(Long id) {
        // Check if the task exists before deleting
        Optional<Task> task = taskRepository.findById(id);
        if (task.isEmpty()) {
            throw new ResourceNotFoundException("Task with id '" + id + "' not found.");
        }

        taskRepository.deleteById(id);

        // Return a success message with the deleted task ID
        return "Task with id " + id + " deleted successfully.";
    }

    private Set<Tag> getOrCreateTags(Set<String> tagNames) {
        return tagNames.stream().map(name -> {
            return tagRepository.findByName(name).orElseGet(() -> {
                Tag tag = new Tag();
                tag.setName(name);
                return tagRepository.save(tag);
            });
        }).collect(Collectors.toSet());
    }

    private TaskDTO mapToDTO(Task task) {
        TaskDTO dto = new TaskDTO();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setCompleted(task.isCompleted());
        dto.setTags(task.getTags().stream().map(Tag::getName).collect(Collectors.toSet()));
        return dto;
    }
}
