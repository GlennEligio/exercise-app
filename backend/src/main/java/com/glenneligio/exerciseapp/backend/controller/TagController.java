package com.glenneligio.exerciseapp.backend.controller;

import com.glenneligio.exerciseapp.backend.dto.TagDto;
import com.glenneligio.exerciseapp.backend.model.Tag;
import com.glenneligio.exerciseapp.backend.repository.TagRepository;
import com.glenneligio.exerciseapp.backend.service.TagService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tags")
public class TagController {

    @Autowired
    private TagService service;

    @GetMapping
    public ResponseEntity<List<Tag>> getTags(@RequestParam(required = false) String name) {
        if(name != null && !name.isBlank()) {
            return ResponseEntity.ok(service.findTagThatStartsWith(name));
        }

        return ResponseEntity.ok(service.getAllTags());
    }

    @PostMapping
    public ResponseEntity<Tag> createTag(@Valid @RequestBody TagDto tagDto) {
        Tag tag = tagDto.toEntity();
        return ResponseEntity.status(201).body(service.createTag(tag));
    }
}
