package com.glenneligio.exerciseapp.backend.repository;

import com.glenneligio.exerciseapp.backend.model.ExercisePlanHistory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ExercisePlanHistoryRepository extends MongoRepository<ExercisePlanHistory, String> {

    List<ExercisePlanHistory> findByCreatorIdOrderByDateFinishedDesc(String id, Pageable pageable);
    List<ExercisePlanHistory> findByCreatorIdOrderByDateFinishedDesc(String id);

}
