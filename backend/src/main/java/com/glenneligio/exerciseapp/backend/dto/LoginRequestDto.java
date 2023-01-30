package com.glenneligio.exerciseapp.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginRequestDto(
        @NotBlank String username,
        @NotBlank String password
) {
}
