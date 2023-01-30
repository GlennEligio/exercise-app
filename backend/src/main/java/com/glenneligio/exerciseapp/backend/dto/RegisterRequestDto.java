package com.glenneligio.exerciseapp.backend.dto;

import com.glenneligio.exerciseapp.backend.model.Account;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterRequestDto(
        @NotBlank String username,
        @NotBlank String password,
        @NotBlank String name,
        @NotBlank String email
) {

    public Account toAccount() {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        account.setName(name);
        account.setEmail(email);
        return account;
    }
}
