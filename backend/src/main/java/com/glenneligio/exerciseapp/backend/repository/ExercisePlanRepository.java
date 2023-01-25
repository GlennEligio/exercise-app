package com.glenneligio.exerciseapp.backend.repository;

import com.glenneligio.exerciseapp.backend.model.ExercisePlan;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ExercisePlanRepository extends MongoRepository<ExercisePlan, String> {

    List<ExercisePlan> findByCreatorId(String creatorId);
    List<ExercisePlan> findByName(String name);
}
