package com.glenneligio.exerciseapp.backend.service;

import com.glenneligio.exerciseapp.backend.exception.ApiException;
import com.glenneligio.exerciseapp.backend.model.Tag;
import com.glenneligio.exerciseapp.backend.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    @Autowired
    private TagRepository repository;

    public List<Tag> findTagThatStartsWith(String name) {
        return repository.findByNameStartingWith(name);
    }

    public Tag createTag(Tag tag) {
        repository.findByName(tag.getName()).ifPresent(tag1 -> {
            throw new ApiException("Tag with same name already exist", HttpStatus.BAD_REQUEST);
        });
        return repository.save(tag);
    }

    public List<Tag> getAllTags() {
        return repository.findAll();
    }
}
