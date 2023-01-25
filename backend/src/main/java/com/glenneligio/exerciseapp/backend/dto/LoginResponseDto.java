package com.glenneligio.exerciseapp.backend.dto;

public record LoginResponseDto(
         String username,
         String accountType,
         String fullName,
         String accessToken,
         String profile
) {
}