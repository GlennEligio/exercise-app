package com.glenneligio.exerciseapp.backend.model;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    @Id
    private String id;
    @Positive
    private int rating;
    @NotNull
    private Account reviewer;
    private String description;
    @FutureOrPresent
    private LocalDateTime dateCreated;
    @NotNull
    private Exercise exercise;
}
