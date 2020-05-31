package com.example.webfluxdemo.functional.handler;

import com.example.webfluxdemo.model.Dept;
import com.example.webfluxdemo.repository.DeptRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;

@Component
public class DeptHandler {
    private final DeptRepository deptRepository;

    public DeptHandler(DeptRepository deptRepository) {
        this.deptRepository = deptRepository;
    }
    public Mono<ServerResponse> listDept(ServerRequest request) {
        Flux<Dept> dept = deptRepository.findAll();
        return ok().contentType(APPLICATION_JSON).body(dept, Dept.class);
    }
    
    public Mono<ServerResponse> getDept(ServerRequest request) {
        String deptId = request.pathVariable("id");
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        Mono<Dept> deptMono = deptRepository.findById(deptId);
        return deptMono.flatMap(dept -> ok()
              .contentType(APPLICATION_JSON)
              .body(fromObject(dept)))
              .switchIfEmpty(notFound);
    }
    
    public Mono<ServerResponse> createDept(ServerRequest request) {
        System.out.println("in create Dept");
        Mono<Dept> dept = request.bodyToMono(Dept.class);

        return ok().build(dept.flatMap(e -> {
            deptRepository.save(e).subscribe();
            return Mono.empty();
        }));
    }
}