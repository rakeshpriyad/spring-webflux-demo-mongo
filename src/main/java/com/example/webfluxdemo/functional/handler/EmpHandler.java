package com.example.webfluxdemo.functional.handler;

import com.example.webfluxdemo.model.Employee;
import com.example.webfluxdemo.repository.EmpRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class EmpHandler {
    private final EmpRepository empRepository;

    public EmpHandler(EmpRepository empRepository) {
        this.empRepository = empRepository;
    }
    public Mono<ServerResponse> listEmp(ServerRequest request) {
        Flux<Employee> emp = empRepository.findAll();
        return ServerResponse.ok().contentType(APPLICATION_JSON).body(emp, Employee.class);
    }
    
    public Mono<ServerResponse> getEmp(ServerRequest request) {
        String empId = request.pathVariable("id");
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        Mono<Employee> empMono = empRepository.findById(empId);
        return empMono.flatMap(emp -> ServerResponse.ok()
              .contentType(APPLICATION_JSON)
              .body(BodyInserters.fromObject(emp)))
              .switchIfEmpty(notFound);
    }
    
    public Mono<ServerResponse> createEmp(ServerRequest request) {
        System.out.println("in create Emp");
        Mono<Employee> emp = request.bodyToMono(Employee.class);
        return ServerResponse.ok().build(saveEmp(emp));
    }

    private Mono<Void> saveEmp(Mono<Employee> emp){
       return emp.flatMap(e -> {
            empRepository.save(e).subscribe();
            return Mono.empty();
        });
    }
}