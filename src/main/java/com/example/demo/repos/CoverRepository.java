package com.example.demo.repos;

import com.example.demo.data.Cover;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CoverRepository extends MongoRepository<Cover, String> {
}
