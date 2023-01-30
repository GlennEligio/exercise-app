package com.glenneligio.exerciseapp.backend.model;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExercisePlanHistory {

    @Id
    private String id;

    @DBRef
    private Account creator;
    @NotEmpty
    private List<Exercise> exercises;

    @FutureOrPresent
    private LocalDateTime dateStarted;
    @FutureOrPresent
    private LocalDateTime dateFinished;
    @Positive
    private int exercisesFinished;
}
