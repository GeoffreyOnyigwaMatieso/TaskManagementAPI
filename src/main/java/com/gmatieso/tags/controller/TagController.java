package com.gmatieso.tags.controller;


import com.gmatieso.tags.model.Tag;
import com.gmatieso.tags.model.Task;
import com.gmatieso.tags.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping
    private ResponseEntity<List<Tag>> getAllTags(){
        return ResponseEntity.ok(tagService.getAllTags());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tag> getTagDetails(@PathVariable Long id) {
        return ResponseEntity.ok(tagService.getTagDetails(id));
    }
}
