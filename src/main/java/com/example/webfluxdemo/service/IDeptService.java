package com.example.webfluxdemo.service;


import com.example.webfluxdemo.model.Dept;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IDeptService
{
    Mono<Dept> create(Dept dept);
     
    Mono<Dept> findById(String deptId);
 
    //Flux<Dept> findByName(String name);
 
    Flux<Dept> findAll();
 
    Mono<Dept> update(Dept dept);
 
    Mono<Void> delete(Dept deptId);
}