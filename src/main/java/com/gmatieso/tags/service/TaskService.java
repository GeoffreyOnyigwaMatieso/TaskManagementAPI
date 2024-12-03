package com.gmatieso.tags.service;


import com.gmatieso.tags.model.Tag;
import com.gmatieso.tags.model.Task;
import com.gmatieso.tags.repository.TagRepository;
import com.gmatieso.tags.repository.TaskRepository;
import jakarta.transaction.Transactional;
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


    @Transactional
    public Task createTask(Task task) {
        Set<Tag> savedTags = task.getTags().stream()
                .map(tag -> {
                    if (tag.getId() == null) {
                        return tagRepository.save(tag); // Save new Tag
                    } else {
                        return tagRepository.findById(Math.toIntExact(tag.getId()))
                                .orElseThrow(() -> new RuntimeException("Tag not found with id: " + tag.getId()));
                    }
                })
                .collect(Collectors.toSet());

        task.setTags(savedTags); // Associate saved tags
        return taskRepository.save(task);
    }


    public Optional<Task> getTaskById(Long id){
         return  taskRepository.findById(id);
     }

     public List<Task> getAllTask() {
        return taskRepository.findAll();
     }

     public void deleteAllTask(){
         taskRepository.deleteAll();

     }

}
