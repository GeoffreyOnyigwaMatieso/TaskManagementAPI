package com.gmatieso.tags.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.Set;

@Data
public class TaskDTO {
    @JsonProperty("task_id")
    private Long id;

    private String title;

    private boolean status;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Set<String> tags;
}


