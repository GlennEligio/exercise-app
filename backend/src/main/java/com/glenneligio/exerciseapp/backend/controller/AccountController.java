package com.glenneligio.exerciseapp.backend.controller;

import com.glenneligio.exerciseapp.backend.dto.*;
import com.glenneligio.exerciseapp.backend.exception.ApiException;
import com.glenneligio.exerciseapp.backend.model.Account;
import com.glenneligio.exerciseapp.backend.model.Exercise;
import com.glenneligio.exerciseapp.backend.model.ExercisePlan;
import com.glenneligio.exerciseapp.backend.service.AccountService;
import com.glenneligio.exerciseapp.backend.util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    @Autowired
    private AccountService service;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto login) {
        Account account = service.verifyAccountLogin(login.username(), login.password());
        MyUserDetails userDetails = new MyUserDetails(account);
        LoginResponseDto responseDto = new LoginResponseDto(
                account.getId(),
                login.username(),
                login.password(),
                account.getName(),
                jwtUtil.generateToken(userDetails),
                account.getProfileUrl()
        );
        return ResponseEntity.ok(responseDto);
    }

    // For Testing in Postman
    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        return ResponseEntity.ok(service.getAllAccounts());
    }

    @PostMapping("/register")
    public ResponseEntity<Account> register(@Valid @RequestBody RegisterRequestDto dto) {
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
                                                            @Valid @RequestBody Exercise exercise) {
        return ResponseEntity.ok(service.updateAccountExercise(username, exercise));
    }

    @PostMapping("/{username}/plans")
    public ResponseEntity<List<ExercisePlan>> addSavedExercisePlans(@PathVariable String username,
                                                                    @Valid @RequestBody ExercisePlan plan) {
        return ResponseEntity.ok(service.updateAccountExercisePlans(username, plan));
    }

    @PutMapping("/{username}")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable String username,
                                                    @Valid @RequestBody AccountDto dto) {
        Account updatedAccount = service.updateAccount(username, AccountDto.toAccount(dto));
        AccountDto responseDto = AccountDto.toDto(updatedAccount);
        return ResponseEntity.ok(responseDto);
    }
}
