package com.glenneligio.exerciseapp.backend.service;

import com.glenneligio.exerciseapp.backend.exception.ApiException;
import com.glenneligio.exerciseapp.backend.model.Account;
import com.glenneligio.exerciseapp.backend.model.Exercise;
import com.glenneligio.exerciseapp.backend.model.ExercisePlanHistory;
import com.glenneligio.exerciseapp.backend.repository.AccountRepository;
import com.glenneligio.exerciseapp.backend.repository.ExercisePlanHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExercisePlanHistoryService {

    @Autowired
    private ExercisePlanHistoryRepository repository;
    @Autowired
    private AccountRepository accountRepository;



    public List<ExercisePlanHistory> getLatestExercisePlanHistoryOfAccount(String id) {
        return repository.findByCreatorIdOrderByDateFinishedDesc(id, PageRequest.of(0, 10));
    }

    public List<Exercise> getLatestExercisesOfAccount(String id) {
        return repository.findByCreatorIdOrderByDateFinishedDesc(id, PageRequest.of(0, 10))
                .stream()
                .flatMap(planHistory -> planHistory.getExercises().stream())
                .toList();
    }

    public List<ExercisePlanHistory> getExercisePlanHistoryOfAccount(String id) {
        return repository.findByCreatorIdOrderByDateFinishedDesc(id);
    }

    public List<Exercise> getExercisesOfAccount(String id) {
        return repository.findByCreatorIdOrderByDateFinishedDesc(id)
                .stream()
                .flatMap(planHistory -> planHistory.getExercises().stream())
                .toList();
    }

    public ExercisePlanHistory createExercisePlanHistory(ExercisePlanHistory planHistory) {
        Account account = accountRepository.findByUsername(planHistory.getCreator().getUsername())
                .orElseThrow(() -> new ApiException("Account not found", HttpStatus.NOT_FOUND));
        planHistory.setCreator(account);
        return repository.save(planHistory);
    }
}
