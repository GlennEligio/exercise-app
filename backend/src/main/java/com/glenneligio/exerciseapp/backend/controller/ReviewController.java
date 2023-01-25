package com.glenneligio.exerciseapp.backend.controller;

import com.glenneligio.exerciseapp.backend.dto.ReviewDto;
import com.glenneligio.exerciseapp.backend.model.Review;
import com.glenneligio.exerciseapp.backend.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    @Autowired
    private ReviewRepository repository;

    @GetMapping
    public ResponseEntity<List<Review>> getReviews(@RequestParam(required = false) String exerciseId) {
        if(exerciseId != null && !exerciseId.isBlank()) {
            return ResponseEntity.ok(repository.getReviewsByExerciseName(exerciseId));
        }
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getReview(@PathVariable String id) {
        Optional<Review> reviewOptional = repository.findById(id);
        return reviewOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody ReviewDto dto) {
        Review review = dto.toReview();
        return ResponseEntity.status(201).body(repository.save(review));
    }
}
