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
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TagRepository tagRepository;

    public TaskDTO createTask(TaskDTO taskDTO) {
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

