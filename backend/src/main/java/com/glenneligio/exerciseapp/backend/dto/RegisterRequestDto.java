package com.glenneligio.exerciseapp.backend.dto;

import com.glenneligio.exerciseapp.backend.model.Account;

public record RegisterRequestDto(
        String username,
        String password,
        String name,
        String email
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
