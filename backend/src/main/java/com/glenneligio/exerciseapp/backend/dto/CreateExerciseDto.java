package com.glenneligio.exerciseapp.backend.dto;

import com.glenneligio.exerciseapp.backend.model.Account;
import com.glenneligio.exerciseapp.backend.model.Exercise;
import com.glenneligio.exerciseapp.backend.model.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record CreateExerciseDto(
        @NotBlank String name,
        Account creator,
        @NotBlank String description,
        @NotEmpty List<Tag>tags,
        String pictureUrl,
        String videoUrl,
        @Positive int defaultSets,
        @Positive int defaultReps,
        @Positive double defaultWeights
) {

    public Exercise toExercise() {
        Exercise exercise = new Exercise();
        exercise.setName(name);
        exercise.setCreator(creator);
        exercise.setDescription(description);
        exercise.setTags(tags);
        exercise.setPictureUrl(pictureUrl);
        exercise.setVideoUrl(videoUrl);
        exercise.setDefaultSets(defaultSets);
        exercise.setDefaultReps(defaultReps);
        exercise.setDefaultWeights(defaultWeights);
        exercise.setRating(0);
        return exercise;
    }
}
