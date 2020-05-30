package com.example.webfluxdemo.service;


import com.example.webfluxdemo.model.Employee;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
 
public interface IEmployeeService
{
    Mono<Employee> create(Employee e);
     
    Mono<Employee> findById(String id);
 
    //Flux<Employee> findByName(String name);
 
    Flux<Employee> findAll();
 
    Mono<Employee> update(Employee e);
 
    Mono<Void> delete(String id);
}