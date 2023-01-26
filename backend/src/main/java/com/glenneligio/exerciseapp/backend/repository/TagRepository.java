package com.glenneligio.exerciseapp.backend.repository;

import com.glenneligio.exerciseapp.backend.model.Tag;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends MongoRepository<Tag, String> {

    List<Tag> findByNameStartingWith(String name);

    Optional<Tag> findByName(String name);
}
