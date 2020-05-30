package com.example.webfluxdemo.repository;

import com.example.webfluxdemo.model.Dept;
import com.example.webfluxdemo.model.Employee;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 */
@Repository
public interface EmpRepository extends ReactiveMongoRepository<Employee, String> {

}
