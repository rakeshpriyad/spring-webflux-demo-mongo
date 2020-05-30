package com.example.webfluxdemo.controller;

import com.example.webfluxdemo.exception.DeptNotFoundException;
import com.example.webfluxdemo.model.Dept;
import com.example.webfluxdemo.payload.ErrorResponse;
import com.example.webfluxdemo.service.DeptService;
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
public class DeptController {

    @Autowired
    private DeptService deptService;

    @GetMapping("/depts")
    public Flux<Dept> getAllDept() {
        return deptService.findAll();
    }

    @PostMapping("/depts")
    public Mono<Dept> createDepts(@Valid @RequestBody Dept dept) {
        return deptService.create(dept);
    }

    @GetMapping("/depts/{id}")
    public Mono<ResponseEntity<Dept>> getDeptById(@PathVariable(value = "id") String deptId) {
        return deptService.findById(deptId)
                .map(savedDept -> ResponseEntity.ok(savedDept))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/depts/{id}")
    public Mono<ResponseEntity<Dept>> updateDept(@PathVariable(value = "id") String deptId,
                                                   @Valid @RequestBody Dept dept) {
        return deptService.findById(deptId)
                .flatMap(existingDept -> {
                    existingDept.setName(dept.getName());
                    return deptService.create(existingDept);
                })
                .map(updateDept -> new ResponseEntity<>(updateDept, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/depts/{id}")
    public Mono<ResponseEntity<Void>> deleteDept(@PathVariable(value = "id") String deptId) {

        return deptService.findById(deptId)
                .flatMap(existingDept ->
                        deptService.delete(existingDept)
                            .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                )
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // depts are Sent to the client as Server Sent Events
    @GetMapping(value = "/stream/depts", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Dept> streamAllDepts() {
        return deptService.findAll();
    }




    /*
        Exception Handling Examples (These can be put into a @ControllerAdvice to handle exceptions globally)
    */

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity handleDuplicateKeyException(DuplicateKeyException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse("A Dept with the same text already exists"));
    }

    @ExceptionHandler(DeptNotFoundException.class)
    public ResponseEntity handleDeptNotFoundException(DeptNotFoundException ex) {
        return ResponseEntity.notFound().build();
    }

}
