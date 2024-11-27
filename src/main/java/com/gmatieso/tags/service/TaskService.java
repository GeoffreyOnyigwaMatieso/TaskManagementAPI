package com.gmatieso.tags.service;

import com.gmatieso.tags.dto.TaskDTO;
import com.gmatieso.tags.model.Task;
import com.gmatieso.tags.model.Tag;
import com.gmatieso.tags.repository.TaskRepository;
import com.gmatieso.tags.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TagRepository tagRepository;

    public TaskDTO getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        return convertToDTO(task);
    }

    public TaskDTO createTask(TaskDTO taskDTO) {
        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        task.setStatus(taskDTO.isStatus());
        Set<Tag> tags = taskDTO.getTags().stream()
                .map(tagName -> tagRepository.findByName(tagName).orElseGet(() -> {
                    Tag tag = new Tag();
                    tag.setName(tagName);
                    return tagRepository.save(tag);
                }))
                .collect(Collectors.toSet());
        task.setTags(tags);
        Task savedTask = taskRepository.save(task);
        return convertToDTO(savedTask);
    }

    public TaskDTO updateTask(Long id, TaskDTO taskDTO) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        task.setTitle(taskDTO.getTitle());
        task.setStatus(taskDTO.isStatus());
        Set<Tag> tags = taskDTO.getTags().stream()
                .map(tagName -> tagRepository.findByName(tagName).orElseGet(() -> {
                    Tag tag = new Tag();
                    tag.setName(tagName);
                    return tagRepository.save(tag);
                }))
                .collect(Collectors.toSet());
        task.setTags(tags);
        Task updatedTask = taskRepository.save(task);
        return convertToDTO(updatedTask);
    }

    private TaskDTO convertToDTO(Task task) {
        TaskDTO dto = new TaskDTO();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setStatus(task.isStatus());
        dto.setTags(task.getTags().stream().map(Tag::getName).collect(Collectors.toSet()));
        return dto;
    }
}
