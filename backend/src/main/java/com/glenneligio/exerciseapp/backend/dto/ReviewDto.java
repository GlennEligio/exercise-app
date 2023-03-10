package com.glenneligio.exerciseapp.backend.dto;

import com.glenneligio.exerciseapp.backend.model.Account;
import com.glenneligio.exerciseapp.backend.model.Exercise;
import com.glenneligio.exerciseapp.backend.model.Review;

public record ReviewDto(
        int rating,
        Account reviewer,
        String description,
        Exercise exercise
) {

    public Review toReview() {
        Review review = new Review();
        review.setExercise(exercise);
        review.setReviewer(reviewer);
        review.setRating(rating);
        review.setDescription(description);
        return review;
    }
}
