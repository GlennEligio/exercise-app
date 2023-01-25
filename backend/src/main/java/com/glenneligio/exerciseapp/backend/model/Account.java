package com.glenneligio.exerciseapp.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    private String id;
    private String name;
    @Indexed(unique = true)
    private String username;
    private String password;
    private String description;
    private String email;
    private String profileUrl;

    @Reference
    private List<ExercisePlan> savedExercisePlans;

    @Reference
    private List<Exercise> savedExercises;
}
