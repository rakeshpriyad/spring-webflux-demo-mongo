package com.example.webfluxdemo.repository;

import com.example.webfluxdemo.model.Dept;
import com.example.webfluxdemo.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 */
@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {

}
