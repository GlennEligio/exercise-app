package com.glenneligio.exerciseapp.backend.service;

import com.glenneligio.exerciseapp.backend.exception.ApiException;
import com.glenneligio.exerciseapp.backend.model.Account;
import com.glenneligio.exerciseapp.backend.model.Exercise;
import com.glenneligio.exerciseapp.backend.model.ExercisePlan;
import com.glenneligio.exerciseapp.backend.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repository;

    public Account createAccount(Account account) {
        Optional<Account> accountOptional = repository.findByUsername(account.getUsername());
        if(accountOptional.isPresent()) throw new ApiException("Account with same username already exist", HttpStatus.BAD_REQUEST);
        return repository.save(account);
    }

    public Account findAccountByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new ApiException("Account with username specified not found", HttpStatus.NOT_FOUND));
    }

    public List<Exercise> updateAccountExercise(String username, Exercise exercise) {
        Account account = repository.findByUsername(username).orElseThrow(() -> new ApiException("Account not found", HttpStatus.NOT_FOUND));
        account.getSavedExercises().add(exercise);
        repository.save(account);
        return account.getSavedExercises();
    }

    public List<ExercisePlan> updateAccountExercisePlans(String username, ExercisePlan plan) {
        Account account = repository.findByUsername(username).orElseThrow(() -> new ApiException("Account not found", HttpStatus.NOT_FOUND));
        account.getSavedExercisePlans().add(plan);
        repository.save(account);
        return account.getSavedExercisePlans();
    }

    public Account updateAccount(String username, Account updatedAccount) {
        Account account = repository.findByUsername(username).orElseThrow(() -> new ApiException("Account not found", HttpStatus.NOT_FOUND));
        account.setUsername(updatedAccount.getUsername());
        // Encode this later using PasswordEncoder
        account.setPassword(updatedAccount.getPassword());
        account.setEmail(updatedAccount.getEmail());
        account.setProfileUrl(updatedAccount.getProfileUrl());
        account.setDescription(updatedAccount.getDescription());
        return repository.save(account);
    }

    public List<Account> getAllAccounts() {
        return repository.findAll();
    }
}
