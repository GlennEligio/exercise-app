package com.glenneligio.exerciseapp.backend.repository;

import com.glenneligio.exerciseapp.backend.model.Tag;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TagRepository extends MongoRepository<Tag, String> {

    List<Tag> findByNameStartingWith(String name);
}
