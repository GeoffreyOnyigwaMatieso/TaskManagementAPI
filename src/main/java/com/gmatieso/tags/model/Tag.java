package com.gmatieso.tags.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of tags")
    @JsonProperty("id")
    private Long id;

    @Schema(description = "name")
    @JsonProperty("name")
    private String name;

    @Schema(description = "tasks")
    @ManyToMany(mappedBy = "tags")
//    @JsonProperty("tasks")
    @JsonIgnore
    private Set<Task> tasks;

}
