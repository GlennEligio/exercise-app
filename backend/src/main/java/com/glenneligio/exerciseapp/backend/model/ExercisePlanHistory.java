package com.glenneligio.exerciseapp.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
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
    private Account creator;
    private List<Exercise> exercises;
    private LocalDateTime dateStarted;
    private LocalDateTime dateFinished;
    private int exercisesFinished;
}
