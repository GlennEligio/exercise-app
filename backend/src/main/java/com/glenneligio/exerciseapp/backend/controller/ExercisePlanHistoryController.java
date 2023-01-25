package com.glenneligio.exerciseapp.backend.controller;

import com.glenneligio.exerciseapp.backend.model.Exercise;
import com.glenneligio.exerciseapp.backend.model.ExercisePlanHistory;
import com.glenneligio.exerciseapp.backend.repository.ExercisePlanHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/history")
public class ExercisePlanHistoryController {

    @Autowired
    private ExercisePlanHistoryRepository repository;

    @PostMapping("/plans")
    public ResponseEntity<ExercisePlanHistory> createPlanHistory(@RequestBody ExercisePlanHistory planHistory) {
        return ResponseEntity.status(201).body(repository.save(planHistory));
    }

    @GetMapping("/exercises/latest")
    public ResponseEntity<List<ExercisePlanHistory>> getLatestExercisePlanHistory(@RequestParam String username) {
        return ResponseEntity.ok(repository.getLatestExercisePlanHistoryOfAccount(username));
    }

    @GetMapping("/plans/latest")
    public ResponseEntity<List<Exercise>> getLatestExerciseHistory(@RequestParam String username) {
        return ResponseEntity.ok(repository.getLatestExercisesOfAccount(username));
    }

    @GetMapping("/exercises")
    public ResponseEntity<List<ExercisePlanHistory>> getExercisePlanHistory(@RequestParam String username) {
        return ResponseEntity.ok(repository.getExercisePlanHistoryOfAccount(username));
    }

    @GetMapping("/plans")
    public ResponseEntity<List<Exercise>> getExerciseHistory(@RequestParam String username) {
        return ResponseEntity.ok(repository.getExercisesOfAccount(username));
    }
}
