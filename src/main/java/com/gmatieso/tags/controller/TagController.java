package com.gmatieso.tags.controller;

import com.gmatieso.tags.dto.TagDTO;
import com.gmatieso.tags.service.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class TagController {

    private static final Logger logger = LoggerFactory.getLogger(TagController.class);

    @Autowired
    private TagService tagService;

    // GET endpoint to fetch all tags
    @GetMapping
    public ResponseEntity<List<TagDTO>> getAllTags() {
        logger.info("Received request to fetch all tags");
        List<TagDTO> tags = tagService.getAllTags();
        logger.info("Successfully fetched {} tags", tags.size());
        return ResponseEntity.ok(tags);
    }

    // GET endpoint to fetch tag by ID
    @GetMapping("/{id}")
    public ResponseEntity<TagDTO> getTagById(@PathVariable Long id) {
        logger.info("Received request to fetch tag with ID: {}", id);
        TagDTO tag = tagService.getTagDetails(id);
        logger.info("Successfully fetched tag with ID: {}", id);
        return ResponseEntity.ok(tag);
    }
}
