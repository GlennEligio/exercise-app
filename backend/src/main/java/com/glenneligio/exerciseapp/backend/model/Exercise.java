package com.glenneligio.exerciseapp.backend.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Exercise {

    @Id
    private String id;

    @NotNull
    @NotBlank
    private String name;

    @DBRef

    private Account creator;
    private double rating;
    @NotBlank
    private String description;
    @DBRef
    @NotEmpty
    private List<Tag> tags;
    private String pictureUrl;
    private String videoUrl;

    @Positive
    private int defaultSets;
    @Positive
    private int defaultReps;
    @Positive
    private double defaultWeights;
    @Positive
    private int currentSets;
    @Positive
    private int currentReps;
    @Positive
    private double currentWeights;
}
