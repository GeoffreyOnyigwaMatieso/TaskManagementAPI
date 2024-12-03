package com.gmatieso.tags.dto;

import com.gmatieso.tags.model.Task;
import com.gmatieso.tags.model.Tag;

import java.util.Set;
import java.util.stream.Collectors;

public class TaskDTO {
    private Long id;
    private String title;
    private boolean completed;
    private Set<String> tags;

    // Constructor to map Task to TaskDTO
    public TaskDTO(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.completed = task.isCompleted();
        this.tags = task.getTags().stream()
                .map(Tag::getName)
                .collect(Collectors.toSet());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }
}
