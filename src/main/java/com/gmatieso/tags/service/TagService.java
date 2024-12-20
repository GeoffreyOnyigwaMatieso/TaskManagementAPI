package com.gmatieso.tags.service;

import com.gmatieso.tags.model.Tag;
import com.gmatieso.tags.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    public  List<Tag> getAllTags() {
        return  tagRepository.findAll();
    }

}
