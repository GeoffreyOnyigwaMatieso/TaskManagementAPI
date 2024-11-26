package com.gmatieso.tags.service;


import com.gmatieso.tags.exception.ResourceNotFoundException;
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

    public Task createTask(Task task){
        task.setTags(saveTags(task.getTags()));
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task taskDetails) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
        task.setTitle(taskDetails.getTitle());
        task.setStatus(taskDetails.isStatus());
        task.setTags(saveTags(taskDetails.getTags()));
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    private Set<Tag> saveTags(Set<Tag> tags) {
        return tags.stream()
                .map(tag -> tagRepository.findByName(tag.getName())
                        .orElseGet(() -> tagRepository.save(tag)))
                .collect(Collectors.toSet());
    }
}
