package com.glenneligio.exerciseapp.backend.controller;

import com.glenneligio.exerciseapp.backend.dto.CreateExerciseDto;
import com.glenneligio.exerciseapp.backend.model.Exercise;
import com.glenneligio.exerciseapp.backend.model.ExercisePlan;
import com.glenneligio.exerciseapp.backend.repository.ExercisePlanRepository;
import jakarta.validation.Valid;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/plans")
public class ExercisePlanController {

    @Autowired
    private ExercisePlanRepository repository;

    @GetMapping
    public ResponseEntity<List<ExercisePlan>> getPlans(@RequestParam(required = false) String creatorId,
                                                       @RequestParam(required = false) String name) {
        if(creatorId != null && ObjectId.isValid(creatorId)) {
            return ResponseEntity.ok(repository.findByCreatorId(creatorId));
        }
        if(name != null && !name.isBlank()) {
            return ResponseEntity.ok(repository.findByName(name));
        }
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExercisePlan> getPlan(@PathVariable String id) {
        Optional<ExercisePlan> exerciseOptional = repository.findById(id);
        return exerciseOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ExercisePlan> createPlan(@Valid @RequestBody ExercisePlan plan) {
        return ResponseEntity.ok(repository.save(plan));
    }
}
