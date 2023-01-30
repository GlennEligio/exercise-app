package com.glenneligio.exerciseapp.backend.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExercisePlan {

    @Id
    private String id;

    @NotBlank
    private String name;

    @NotEmpty
    private List<Exercise> exercises;

    private Account creator;
    @NotBlank
    private String description;
}
