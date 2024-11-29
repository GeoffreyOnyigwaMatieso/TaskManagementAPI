package com.gmatieso.tags.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TagDTO {
    @JsonProperty("tag_id")
    private Long id;
    private String name;
}
