package com.glenneligio.exerciseapp.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
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
    private String name;

    @DBRef

    private Account creator;
    private double rating;
    private String description;
    @DBRef
    private List<Tag> tags;
    private String pictureUrl;
    private String videoUrl;
    private int defaultSets;
    private int defaultReps;
    private double defaultWeights;
    private int currentSets;
    private int currentReps;
    private double currentWeights;
}
