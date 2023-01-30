package com.glenneligio.exerciseapp.backend.model;

import com.glenneligio.exerciseapp.backend.enums.Role;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    private String id;

    @NotBlank
    private String name;
    @Indexed(unique = true)
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    private String description;
    @NotBlank
    private String email;
    private String profileUrl;

    private Role role;

    private boolean active;

    @Reference
    private List<ExercisePlan> savedExercisePlans;

    @Reference
    private List<Exercise> savedExercises;
}
