package com.example.webfluxdemo.repository;

import com.example.webfluxdemo.model.Dept;
import com.example.webfluxdemo.model.Tweet;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 */
@Repository
public interface DeptRepository extends ReactiveMongoRepository<Dept, String> {

}
