package com.gmatieso.tags.service;

import com.gmatieso.tags.dto.TagDTO;
import com.gmatieso.tags.model.Tag;
import com.gmatieso.tags.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagService {

    private static final Logger logger = LoggerFactory.getLogger(TagService.class);

    @Autowired
    private TagRepository tagRepository;

    public List<TagDTO> getAllTags() {
        logger.info("Fetching all tags.");
        return tagRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public TagDTO getTagById(Long id) {
        logger.info("Fetching tag details for ID: {}", id);
        Tag tag = tagRepository.findById(id).orElseThrow(() -> {
            logger.error("Tag with ID '{}' not found.", id);
            return new ResourceNotFoundException("Tag not found with id: " + id);
        });
        logger.info("Fetched tag details successfully for ID: {}", id);
        return mapToDTO(tag);
    }

    private TagDTO mapToDTO(Tag tag) {
        logger.info("Mapping Tag entity to TagDTO for tag ID: {}", tag.getId());
        TagDTO dto = new TagDTO();
        dto.setId(tag.getId());
        dto.setName(tag.getName());
        dto.setTasks(tag.getTasks().stream().map(task -> task.getTitle()).collect(Collectors.toSet()));
        return dto;
    }
}
