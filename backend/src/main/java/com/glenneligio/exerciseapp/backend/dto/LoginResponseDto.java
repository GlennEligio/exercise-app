package com.glenneligio.exerciseapp.backend.dto;

public record LoginResponseDto(
        String id,
        String username,
        String accountType,
        String name,
        String accessToken,
        String profile
) {
}