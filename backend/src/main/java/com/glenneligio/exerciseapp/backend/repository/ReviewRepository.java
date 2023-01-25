package com.glenneligio.exerciseapp.backend.repository;

import com.glenneligio.exerciseapp.backend.model.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ReviewRepository extends MongoRepository<Review, String> {

    @Query("{ 'exercise.id' : ?0 }")
    List<Review> getReviewsByExerciseName(String exerciseId);
}
