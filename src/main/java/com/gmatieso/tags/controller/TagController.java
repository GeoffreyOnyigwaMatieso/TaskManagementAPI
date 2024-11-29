package com.gmatieso.tags.controller;

import com.gmatieso.tags.dto.TagDTO;
import com.gmatieso.tags.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tags")
public class TagController {

    @Autowired
    private TagService tagService;

//    @GetMapping("/{id}")
//    public ResponseEntity<TagDTO> getTagById(@PathVariable Long id) {
//        return ResponseEntity.ok(tagService.getTagById(id));
//    }
}
