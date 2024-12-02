package com.gmatieso.tags.controller;

import com.gmatieso.tags.dto.TaskDTO;
import com.gmatieso.tags.model.Task;
import com.gmatieso.tags.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;


    @Operation(summary = "Create new task")
    @ApiResponse(responseCode = "201", description = "Task created successfully")
    @PostMapping
    public Task createTask(@RequestBody  Task task) {
       return taskService.createTask(task);
    }

    @Operation(summary = "Get All Task", description = "Return list of all task")
    @ApiResponse(responseCode = "200", description = "Task created successfully")
    @GetMapping
    public  List<Task>getAllTask(){
        return  taskService.getAllTask();
    }

    @Operation(summary = "Get Task By Id", description = "Return a single Task")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Task Found"),
            @ApiResponse(responseCode = "404", description = "Task Not Found")
    })
    @GetMapping("/{id}")
    public Optional<Task> getTaskById(@PathVariable int id){
        return taskService.getTaskById(id);
    }


    @Operation(summary = "Delete All Task")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Task Deleted Successfully"),
            @ApiResponse(responseCode = "404", description = "Task Not Found")
    })
    @DeleteMapping
    public void deleteAllTask(){
         taskService.deleteAllTask();
    }

}
