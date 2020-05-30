package com.example.webfluxdemo.service;

import com.example.webfluxdemo.model.Employee;
import com.example.webfluxdemo.repository.EmpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
 
@Service
public class EmployeeService implements IEmployeeService {
     
    @Autowired
    EmpRepository employeeRepo;
 
    public Mono<Employee> create(Employee e) {
        return employeeRepo.save(e);//.subscribe();
    }
 
    public Mono<Employee> findById(String id) {
        return employeeRepo.findById(id);
    }
 
    /*public Flux<Employee> findByName(String name) {
        return employeeRepo.findByName(name);
    }*/
 
    public Flux<Employee> findAll() {
        return employeeRepo.findAll();
    }

    public Mono<Employee> update(Employee e) {
        return employeeRepo.save(e);
    }

    public Mono<Void> delete(String empId) {
        return employeeRepo.deleteById(empId);
    }
 
}