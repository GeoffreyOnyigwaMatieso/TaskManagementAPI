package com.gmatieso.tags.dto;


import lombok.Data;

import java.util.Set;

@Data
public class TagDTO {
    private Long id;
    private String name;
    private Set<String> tasks
}
