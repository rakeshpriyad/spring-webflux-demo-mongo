package com.example.webfluxdemo.service;

import com.example.webfluxdemo.model.Dept;
import com.example.webfluxdemo.repository.DeptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DeptService implements IDeptService {
     
    @Autowired
    DeptRepository deptRepository;
 
    public Mono<Dept> create(Dept e) {
        return deptRepository.save(e);//.subscribe();
    }
 
    public Mono<Dept> findById(String id) {
        return deptRepository.findById(id);
    }
 
    /*public Flux<Dept> findByName(String name) {
        return employeeRepo.findByName(name);
    }*/
 
    public Flux<Dept> findAll() {
        return deptRepository.findAll();
    }

    public Mono<Dept> update(Dept e) {
        return deptRepository.save(e);
    }

    public Mono<Void> delete(Dept dept) {
        return deptRepository.delete(dept);
    }
 
}