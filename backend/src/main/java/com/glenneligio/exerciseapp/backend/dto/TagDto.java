package com.glenneligio.exerciseapp.backend.dto;

import com.glenneligio.exerciseapp.backend.model.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TagDto(
        String id,
        @NotBlank @NotNull String name) {

    public Tag toEntity() {
        Tag tag = new Tag();
        tag.setName(name);
        return tag;
    }
}
