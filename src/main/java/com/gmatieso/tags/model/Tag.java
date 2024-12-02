package com.gmatieso.tags.model;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of tags")
    private Long id;

    @Schema(description = "name")
    private String name;

    @Schema(description = "tasks")
    @ManyToMany(mappedBy = "tags")
    private Set<Task> tasks;
}
