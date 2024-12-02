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

//    public TaskDTO updateTask(Long id, TaskDTO taskDTO) {
//        // Find the task by ID
//        Optional<Task> existingTaskOpt = taskRepository.findById(id);
//        if (existingTaskOpt.isEmpty()) {
//            throw new ResourceNotFoundException("Task with id '" + id + "' not found.");
//        }
//
//        Task existingTask = existingTaskOpt.get();
//        existingTask.setTitle(taskDTO.getTitle());
//        existingTask.setCompleted(taskDTO.isCompleted());
//        existingTask.setTags(getOrCreateTags(taskDTO.getTags()));
//
//        Task updatedTask = taskRepository.save(existingTask);
//        return mapToDTO(updatedTask);
//    }

//    public String deleteTask(Long id) {
//        // Check if the task exists before deleting
//        Optional<Task> task = taskRepository.findById(id);
//        if (task.isEmpty()) {
//            throw new ResourceNotFoundException("Task with id '" + id + "' not found.");
//        }
//
//        taskRepository.deleteById(id);
//
//        // Return a success message with the deleted task ID
//        return "Task with id " + id + " deleted successfully.";
//    }

}
