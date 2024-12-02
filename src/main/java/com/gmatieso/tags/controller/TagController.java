package com.gmatieso.tags.controller;


import com.gmatieso.tags.model.Tag;
import com.gmatieso.tags.service.TagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @Operation(summary = "Get all tags")
    @ApiResponse(responseCode = "200", description = "Return list of all tags")
    @GetMapping
    public List<Tag>getAllTags(){
        return tagService.getAllTags();
    }

}