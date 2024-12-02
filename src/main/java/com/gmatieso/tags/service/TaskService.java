package com.gmatieso.tags.service;


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


     public  Task createTask(Task task) {
         return taskRepository.save(task);
     }

     public Optional<Task> getTaskById(Integer id){
         return  taskRepository.findById(id);
     }

     public List<Task> getAllTask() {
        return taskRepository.findAll();
     }

     public void deleteAllTask(){
         taskRepository.deleteAll();

     }

}
