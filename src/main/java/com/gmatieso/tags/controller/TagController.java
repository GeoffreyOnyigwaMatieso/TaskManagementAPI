package com.gmatieso.tags.controller;

import com.gmatieso.tags.dto.TagDTO;
import com.gmatieso.tags.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    // Endpoint to get all tags
    @GetMapping
    public ResponseEntity<List<TagDTO>> getAllTags() {
        List<TagDTO> tags = tagService.getAllTags();
        return ResponseEntity.ok(tags);
    }

    // Endpoint to get details of a single tag by ID
    @GetMapping("/{id}")
    public ResponseEntity<TagDTO> getTagDetails(@PathVariable Long id) {
        TagDTO tagDetails = tagService.getTagDetails(id);
        return ResponseEntity.ok(tagDetails);
    }
}