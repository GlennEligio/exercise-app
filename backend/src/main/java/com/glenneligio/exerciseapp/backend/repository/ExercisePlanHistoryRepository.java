package com.glenneligio.exerciseapp.backend.repository;

import com.glenneligio.exerciseapp.backend.model.Exercise;
import com.glenneligio.exerciseapp.backend.model.ExercisePlanHistory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ExercisePlanHistoryRepository extends MongoRepository<ExercisePlanHistory, String> {

    List<ExercisePlanHistory> findByCreatorIdOrderByDateFinishedDesc(String username, Pageable pageable);
    List<ExercisePlanHistory> findByCreatorIdOrderByDateFinishedDesc(String username);

    default List<ExercisePlanHistory> getLatestExercisePlanHistoryOfAccount(String username) {
        return findByCreatorIdOrderByDateFinishedDesc(username, PageRequest.of(0, 10));
    }

    default List<Exercise> getLatestExercisesOfAccount(String username) {
        return findByCreatorIdOrderByDateFinishedDesc(username, PageRequest.of(0, 10))
                .stream()
                .flatMap(planHistory -> planHistory.getExercises().stream())
                .toList();
    }

    default List<ExercisePlanHistory> getExercisePlanHistoryOfAccount(String username) {
        return findByCreatorIdOrderByDateFinishedDesc(username);
    }

    default List<Exercise> getExercisesOfAccount(String username) {
        return findByCreatorIdOrderByDateFinishedDesc(username)
                .stream()
                .flatMap(planHistory -> planHistory.getExercises().stream())
                .toList();
    }
}
