package com.glenneligio.exerciseapp.backend.dto;

import com.glenneligio.exerciseapp.backend.model.Account;
import com.glenneligio.exerciseapp.backend.model.Exercise;
import com.glenneligio.exerciseapp.backend.model.Tag;

import java.util.List;

public record CreateExerciseDto(
        String name,
        Account creator,
        String description,
        List<Tag>tags,
        String pictureUrl,
        String videoUrl,
        int defaultSets,
        int defaultReps,
        double defaultWeights
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
