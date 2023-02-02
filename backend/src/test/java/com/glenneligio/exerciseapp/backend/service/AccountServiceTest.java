package com.glenneligio.exerciseapp.backend.service;

import com.glenneligio.exerciseapp.backend.model.Account;
import com.glenneligio.exerciseapp.backend.repository.AccountRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AccountServiceTest {

    @InjectMocks
    private AccountService service;

    @Mock
    private AccountRepository repository;
    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    private Account a0, a1, a2;

}
