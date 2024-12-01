package com.gmatieso.tags.service;

import com.gmatieso.tags.dto.TagDTO;
import com.gmatieso.tags.model.Tag;
import com.gmatieso.tags.repository.TagRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagService {

    private static final Logger logger = LoggerFactory.getLogger(TagService.class);

    @Autowired
    private TagRepository tagRepository;

    public List<TagDTO> getAllTags() {
        logger.info("Fetching all tags...");
        List<TagDTO> tags = tagRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
        logger.info("Fetched {} tags successfully", tags.size());
        return tags;
    }

    public TagDTO getTagDetails(Long id) {
        logger.info("Fetching details for tag with ID: {}", id);
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Tag not found with ID: {}", id);
                    return new ResourceNotFoundException("Tag not found with ID: " + id);
                });
        logger.info("Fetched details for tag with ID: {}", id);
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
