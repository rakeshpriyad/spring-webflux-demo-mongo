package com.example.webfluxdemo;

import com.example.webfluxdemo.functional.handler.EmpHandler;
import com.example.webfluxdemo.repository.EmpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class EmpRouter {
    @Autowired
    EmpHandler empHandler;
   // EmpRepository repository;
    @Bean
    public RouterFunction<ServerResponse> empRoute() {
       // EmpHandler empHandler = new EmpHandler(repository);
        return RouterFunctions
            .route(GET("/emp/{id}").and(accept(APPLICATION_JSON)), empHandler::getEmp)
            .andRoute(GET("/empList").and(accept(APPLICATION_JSON)), empHandler::listEmp)
            .andRoute(POST("/emp/create").and(contentType(APPLICATION_JSON)), empHandler::createEmp);
        
    }
}