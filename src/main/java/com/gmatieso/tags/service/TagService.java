package com.gmatieso.tags.service;

import com.gmatieso.tags.dto.TagDTO;
import com.gmatieso.tags.model.Tag;
import com.gmatieso.tags.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    public List<TagDTO> getAllTags() {
        return tagRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public TagDTO getTagDetails(Long id) {
        Tag tag = tagRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tag not found with id: " + id));
        return mapToDTO(tag);
    }

    private TagDTO mapToDTO(Tag tag) {
        TagDTO dto = new TagDTO();
        dto.setId(tag.getId());
        dto.setName(tag.getName());
        dto.setTasks(tag.getTasks().stream().map(task -> task.getTitle()).collect(Collectors.toSet()));
        return dto;
    }
}
