package com.glenneligio.exerciseapp.backend.controller;

import com.glenneligio.exerciseapp.backend.dto.TagDto;
import com.glenneligio.exerciseapp.backend.model.Tag;
import com.glenneligio.exerciseapp.backend.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tags")
public class TagController {

    @Autowired
    private TagRepository repository;

    @GetMapping
    public ResponseEntity<List<Tag>> getTags(@RequestParam(required = false) String name) {
        if(name != null && !name.isBlank()) {
            return ResponseEntity.ok(repository.findByNameStartingWith(name));
        }

        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping
    public ResponseEntity<Tag> createTag(@RequestBody TagDto tagDto) {
        Tag tag = tagDto.toEntity();
        return ResponseEntity.status(201).body(repository.save(tag));
    }
}
