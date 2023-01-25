package com.glenneligio.exerciseapp.backend.dto;

import com.glenneligio.exerciseapp.backend.model.Account;

import java.util.ArrayList;

public record AccountDto(
        String id,
        String name,
        String username,
        String password,
        String description,
        String email,
        String profileUrl
        ) {

        public static AccountDto toDto(Account account) {
                return new AccountDto(account.getId(),
                        account.getName(),
                        account.getUsername(),
                        null,
                        account.getDescription(),
                        account.getEmail(),
                        account.getProfileUrl());
        }

        public static Account toAccount(AccountDto dto) {
                Account account = new Account();
                account.setName(dto.name);
                account.setPassword(dto.password);
                account.setDescription(dto.description);
                account.setEmail(dto.email);
                account.setProfileUrl(dto.profileUrl);
                account.setSavedExercisePlans(new ArrayList<>());
                account.setSavedExercises(new ArrayList<>());
                return account;
        }
}
