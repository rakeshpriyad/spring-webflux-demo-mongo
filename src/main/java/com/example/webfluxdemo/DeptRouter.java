package com.example.webfluxdemo;

import com.example.webfluxdemo.functional.handler.DeptHandler;
import com.example.webfluxdemo.repository.DeptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class DeptRouter {
   // @Autowired
    //DeptRepository repository;
   @Autowired
   DeptHandler  deptHandler;
    @Bean
    public RouterFunction<ServerResponse> deptRoute() {
       // DeptHandler deptHandler = new DeptHandler(repository);

        return RouterFunctions
            .route(GET("/dept/{id}").and(accept(APPLICATION_JSON)), deptHandler::getDept)
            .andRoute(GET("/deptList").and(accept(APPLICATION_JSON)), deptHandler::listDept)
            .andRoute(POST("/dept/create").and(contentType(APPLICATION_JSON)), deptHandler::createDept);
        
    }
}