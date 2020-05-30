package com.example.webfluxdemo.controller;

import com.example.webfluxdemo.exception.EmpNotFoundException;
import com.example.webfluxdemo.model.Employee;
import com.example.webfluxdemo.payload.ErrorResponse;
import com.example.webfluxdemo.repository.EmpRepository;
import com.example.webfluxdemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

/**
 */
@RestController
public class EmpController {

    @Autowired
    private EmpRepository empRepository;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/emps")
    public Flux<Employee> getAllEmployee() {
        return employeeService.findAll();
    }

    @PostMapping("/emps")
    public Mono<Employee> createEmployees(@Valid @RequestBody Employee emp) {
        return employeeService.create(emp);
    }

    @GetMapping("/emps/{id}")
    public Mono<ResponseEntity<Employee>> getEmployeeById(@PathVariable(value = "id") String empId) {
        return employeeService.findById(empId)
                .map(savedEmployee -> ResponseEntity.ok(savedEmployee))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/emps/{id}")
    public Mono<ResponseEntity<Employee>> updateEmployee(@PathVariable(value = "id") String empId,
                                                          @Valid @RequestBody Employee emp) {
        return employeeService.findById(empId)
                .flatMap(existingEmployee -> {
                    existingEmployee.setName(emp.getName());
                    return employeeService.create(existingEmployee);
                })
                .map(updateEmployee -> new ResponseEntity<>(updateEmployee, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/emps/{id}")
    public Mono<ResponseEntity<Void>> deleteEmployee(@PathVariable(value = "id") String empId) {

        return employeeService.findById(empId)
                .flatMap(existingEmployee ->
                        empRepository.delete(existingEmployee)
                            .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                )
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // emps are Sent to the client as Server Sent Events
    @GetMapping(value = "/stream/emps", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Employee> streamAllEmployees() {
        return employeeService.findAll();
    }




    /*
        Exception Handling Examples (These can be put into a @ControllerAdvice to handle exceptions globally)
    */

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity handleDuplicateKeyException(DuplicateKeyException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse("A Employee with the same text already exists"));
    }

    @ExceptionHandler(EmpNotFoundException.class)
    public ResponseEntity handleEmployeeNotFoundException(EmpNotFoundException ex) {
        return ResponseEntity.notFound().build();
    }

}
