package com.gmatieso.tags.dto;


import lombok.Data;
import java.util.Set;

@Data
public class TaskDTO {
  
    private Long id;
    private String title;
    private boolean completed;
    private Set<String> tags;
}

