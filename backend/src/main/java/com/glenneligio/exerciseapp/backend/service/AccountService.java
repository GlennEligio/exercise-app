package com.glenneligio.exerciseapp.backend.service;

import com.glenneligio.exerciseapp.backend.dto.MyUserDetails;
import com.glenneligio.exerciseapp.backend.enums.Role;
import com.glenneligio.exerciseapp.backend.exception.ApiException;
import com.glenneligio.exerciseapp.backend.model.Account;
import com.glenneligio.exerciseapp.backend.model.Exercise;
import com.glenneligio.exerciseapp.backend.model.ExercisePlan;
import com.glenneligio.exerciseapp.backend.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements UserDetailsService {

    @Autowired
    private AccountRepository repository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Account createAccount(Account account) {
        Optional<Account> accountOptional = repository.findByUsername(account.getUsername());
        if(accountOptional.isPresent()) throw new ApiException("Account with same username already exist", HttpStatus.BAD_REQUEST);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.setRole(Role.USER);
        return repository.save(account);
    }

    public Account findAccountByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new ApiException("Account with username specified not found", HttpStatus.NOT_FOUND));
    }

    // TODO: Add method for adding new exercise
    public List<Exercise> updateAccountExercise(String username, Exercise exercise) {
        Account account = repository.findByUsername(username).orElseThrow(() -> new ApiException("Account not found", HttpStatus.NOT_FOUND));
        account.getSavedExercises().add(exercise);
        repository.save(account);
        return account.getSavedExercises();
    }

    // TODO: Add method for adding new exercise plan
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
        account.setPassword(passwordEncoder.encode(updatedAccount.getPassword()));
        account.setEmail(updatedAccount.getEmail());
        account.setProfileUrl(updatedAccount.getProfileUrl());
        account.setDescription(updatedAccount.getDescription());
        return repository.save(account);
    }

    public List<Account> getAllAccounts() {
        return repository.findAll();
    }

    @Override
    public MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new MyUserDetails(repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Account with username " + username + " was not found")));
    }

    public Account verifyAccountLogin(String username, String password) {
        Account account = repository.findByUsername(username)
                .orElseThrow(() -> new ApiException("Account not found", HttpStatus.NOT_FOUND));
        boolean validCred = passwordEncoder.matches(password, account.getPassword());
        if(!validCred) throw new ApiException("Invalid credentials", HttpStatus.BAD_REQUEST);
        return account;
    }
}
