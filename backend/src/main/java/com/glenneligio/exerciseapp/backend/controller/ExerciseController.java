package com.glenneligio.exerciseapp.backend.controller;

import com.glenneligio.exerciseapp.backend.dto.CreateExerciseDto;
import com.glenneligio.exerciseapp.backend.model.Exercise;
import com.glenneligio.exerciseapp.backend.repository.ExerciseRepository;
import com.glenneligio.exerciseapp.backend.service.ExerciseService;
import jakarta.validation.Valid;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<Exercise> createExercise(@Valid @RequestBody CreateExerciseDto dto) {
        Exercise exercise = dto.toExercise();
        return ResponseEntity.ok(service.createExercise(exercise));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExercise(@PathVariable String id) {
        service.deleteExercise(id);
        return ResponseEntity.ok().build();
    }
}
