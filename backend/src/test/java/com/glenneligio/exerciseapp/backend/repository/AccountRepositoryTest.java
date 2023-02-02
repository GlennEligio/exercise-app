package com.glenneligio.exerciseapp.backend.repository;

import com.glenneligio.exerciseapp.backend.enums.Role;
import com.glenneligio.exerciseapp.backend.model.Account;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
@Testcontainers
@Slf4j
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository repository;

    @Container
    public static MongoDBContainer container = new MongoDBContainer(DockerImageName.parse("mongo:4.4.3"));

    @DynamicPropertySource
    static void mongoDbProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", container::getReplicaSetUrl);
    }

    private Account a0, a1, a2;

    @BeforeAll
    static void initAll() {
        container.start();
    }

    @BeforeEach
    void setupEach() {
        a0 = Account.builder()
                .username("Username0")
                .id(new ObjectId().toHexString())
                .password("Password0")
                .name("Name0")
                .description("Description0")
                .email("Email0")
                .profileUrl("https://sampleimage0.com")
                .role(Role.USER)
                .active(true)
                .savedExercisePlans(new ArrayList<>())
                .savedExercises(new ArrayList<>())
                .build();
        a1 = Account.builder()
                .username("Username1")
                .id(new ObjectId().toHexString())
                .password("Password1")
                .name("Name1")
                .description("Description1")
                .email("Email1")
                .profileUrl("https://sampleimage1.com")
                .role(Role.USER)
                .active(true)
                .savedExercisePlans(new ArrayList<>())
                .savedExercises(new ArrayList<>())
                .build();
        a2 = Account.builder()
                .username("Username2")
                .id(new ObjectId().toHexString())
                .password("Password2")
                .name("Name2")
                .description("Description2")
                .email("Email2")
                .profileUrl("https://sampleimage2.com")
                .role(Role.USER)
                .active(true)
                .savedExercisePlans(new ArrayList<>())
                .savedExercises(new ArrayList<>())
                .build();
        repository.saveAll(List.of(a1, a2, a0));
    }

    @Test
    @DisplayName("Find Account by User using valid Username")
    void findByUsername_withValidUsername_returnAccount() {
        String validUsername = a0.getUsername();
        Optional<Account> accountOptional = repository.findByUsername(validUsername);

        assertNotNull(accountOptional);
        assertEquals(accountOptional.get(), a0);
    }
}
