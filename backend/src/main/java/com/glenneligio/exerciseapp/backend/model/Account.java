package com.glenneligio.exerciseapp.backend.model;

import com.glenneligio.exerciseapp.backend.enums.Role;
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
    private String name;
    @Indexed(unique = true)
    private String username;
    private String password;
    private String description;
    private String email;
    private String profileUrl;

    private Role role;

    private boolean active;

    @Reference
    private List<ExercisePlan> savedExercisePlans;

    @Reference
    private List<Exercise> savedExercises;
}
