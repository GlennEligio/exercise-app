package com.glenneligio.exerciseapp.backend.controller;

import com.glenneligio.exerciseapp.backend.dto.CreateExerciseDto;
import com.glenneligio.exerciseapp.backend.model.Exercise;
import com.glenneligio.exerciseapp.backend.repository.ExerciseRepository;
import com.glenneligio.exerciseapp.backend.service.ExerciseService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/exercises")
public class ExerciseController {

    @Autowired
    private ExerciseService service;

    @GetMapping
    public ResponseEntity<List<Exercise>> getExercises(@RequestParam(required = false) String creatorId,
                                                      @RequestParam(required = false) String name) {
        if(creatorId != null && ObjectId.isValid(creatorId)) {
            return ResponseEntity.ok(service.findExerciseByCreatorId(creatorId));
        }
        if(name != null && !name.isBlank()) {
            return ResponseEntity.ok(service.findExerciseByName(name));
        }
        return ResponseEntity.ok(service.getAllExercise());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exercise> getExercise(@PathVariable String id) {
        Exercise exercise = service.getExerciseById(id);
        return ResponseEntity.ok(exercise);
    }

    @PostMapping
    public ResponseEntity<Exercise> createExercise(@RequestBody Exercise exercise) {
        return ResponseEntity.ok(service.createExercise(exercise));
    }
}
