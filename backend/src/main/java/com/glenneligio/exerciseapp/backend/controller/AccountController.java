package com.glenneligio.exerciseapp.backend.controller;

import com.glenneligio.exerciseapp.backend.dto.AccountDto;
import com.glenneligio.exerciseapp.backend.dto.RegisterRequestDto;
import com.glenneligio.exerciseapp.backend.dto.LoginRequestDto;
import com.glenneligio.exerciseapp.backend.dto.LoginResponseDto;
import com.glenneligio.exerciseapp.backend.model.Account;
import com.glenneligio.exerciseapp.backend.model.Exercise;
import com.glenneligio.exerciseapp.backend.model.ExercisePlan;
import com.glenneligio.exerciseapp.backend.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    @Autowired
    private AccountService service;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto login) {
        // Add login after implementing Spring Security

        return null;
    }

    // For Testing in Postman
    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        return ResponseEntity.ok(service.getAllAccounts());
    }

    @PostMapping("/register")
    public ResponseEntity<Account> register(@RequestBody RegisterRequestDto dto) {
        Account account = service.createAccount(dto.toAccount());
        return ResponseEntity.status(200).body(account);
    }

    @GetMapping("/{username}/exercises")
    public ResponseEntity<List<Exercise>> getSavedExercises(@PathVariable String username) {
        Account account = service.findAccountByUsername(username);
        return ResponseEntity.ok(account.getSavedExercises());
    }

    @GetMapping("/{username}/plans")
    public ResponseEntity<List<ExercisePlan>> getSavedExercisePlans(@PathVariable String username) {
        Account account = service.findAccountByUsername(username);
        return ResponseEntity.ok(account.getSavedExercisePlans());
    }

    @PostMapping("/{username}/exercises")
    public ResponseEntity<List<Exercise>> addSavedExercises(@PathVariable String username,
                                                            @RequestBody Exercise exercise) {
        return ResponseEntity.ok(service.updateAccountExercise(username, exercise));
    }

    @PostMapping("/{username}/plans")
    public ResponseEntity<List<ExercisePlan>> addSavedExercisePlans(@PathVariable String username,
                                                                    @RequestBody ExercisePlan plan) {
        return ResponseEntity.ok(service.updateAccountExercisePlans(username, plan));
    }

    @PutMapping("/{username}")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable String username,
                                                    @RequestBody AccountDto dto) {
        Account updatedAccount = service.updateAccount(username, AccountDto.toAccount(dto));
        AccountDto responseDto = AccountDto.toDto(updatedAccount);
        return ResponseEntity.ok(responseDto);
    }
}
