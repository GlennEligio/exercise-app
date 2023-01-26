package com.glenneligio.exerciseapp.backend.service;

import com.glenneligio.exerciseapp.backend.exception.ApiException;
import com.glenneligio.exerciseapp.backend.model.Account;
import com.glenneligio.exerciseapp.backend.model.Exercise;
import com.glenneligio.exerciseapp.backend.model.Tag;
import com.glenneligio.exerciseapp.backend.repository.AccountRepository;
import com.glenneligio.exerciseapp.backend.repository.ExerciseRepository;
import com.glenneligio.exerciseapp.backend.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ExerciseRepository exerciseRepository;
    @Autowired
    private TagRepository tagRepository;

    public List<Exercise> getAllExercise() {
        return exerciseRepository.findAll();
    }

    public Exercise getExerciseById(String id) {
        return exerciseRepository.findById(id).orElseThrow(() -> new ApiException("Exercise not found", HttpStatus.NOT_FOUND));
    }

    public Exercise createExercise(Exercise exercise) {
        Account account = accountRepository.findByUsername(exercise.getCreator().getUsername())
                .orElseThrow(() -> new ApiException("Account not found", HttpStatus.NOT_FOUND));
        List<Tag> tags = exercise.getTags().stream()
                        .map(tag -> tagRepository.findById(tag.getId()).orElseThrow(() -> new ApiException("Tag not found", HttpStatus.NOT_FOUND)))
                        .toList();
        exercise.setCreator(account);
        exercise.setTags(tags);
        exercise.setRating(0);
        exercise.setCurrentReps(exercise.getDefaultReps());
        exercise.setCurrentSets(exercise.getDefaultSets());
        exercise.setCurrentWeights(exercise.getDefaultWeights());
        return exerciseRepository.save(exercise);
    }

    public List<Exercise> findExerciseByCreatorId(String id) {
        return exerciseRepository.findByCreatorId(id);
    }

    public List<Exercise> findExerciseByName(String name) {
        return exerciseRepository.findByNameStartingWith(name);
    }

    public void deleteExercise(String id) {
        exerciseRepository.findById(id)
                .ifPresentOrElse(exercise -> exerciseRepository.deleteById(exercise.getId()),
                        () -> {throw new ApiException("Exercise not found", HttpStatus.NOT_FOUND);});
    }
}
