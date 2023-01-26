package com.glenneligio.exerciseapp.backend.controller;

import com.glenneligio.exerciseapp.backend.model.Exercise;
import com.glenneligio.exerciseapp.backend.model.ExercisePlanHistory;
import com.glenneligio.exerciseapp.backend.service.ExercisePlanHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/history")
public class ExercisePlanHistoryController {

    @Autowired
    private ExercisePlanHistoryService service;

    @PostMapping("/plans")
    public ResponseEntity<ExercisePlanHistory> createPlanHistory(@RequestBody ExercisePlanHistory planHistory) {
        return ResponseEntity.status(201).body(service.createExercisePlanHistory(planHistory));
    }

    @GetMapping("/exercises/latest")
    public ResponseEntity<List<Exercise>> getLatestExercisePlanHistory(@RequestParam String id) {
        return ResponseEntity.ok(service.getLatestExercisesOfAccount(id));
    }

    @GetMapping("/plans/latest")
    public ResponseEntity<List<Exercise>> getLatestExerciseHistory(@RequestParam String id) {
        return ResponseEntity.ok(service.getLatestExercisesOfAccount(id));
    }

    @GetMapping("/exercises")
    public ResponseEntity<List<Exercise>> getExercisePlanHistory(@RequestParam String id) {
        return ResponseEntity.ok(service.getExercisesOfAccount(id));
    }

    @GetMapping("/plans")
    public ResponseEntity<List<ExercisePlanHistory>> getExerciseHistory(@RequestParam String id) {
        return ResponseEntity.ok(service.getExercisePlanHistoryOfAccount(id));
    }
}
