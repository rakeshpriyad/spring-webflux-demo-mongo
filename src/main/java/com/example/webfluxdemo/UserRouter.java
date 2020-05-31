package com.example.webfluxdemo;

import com.example.webfluxdemo.functional.handler.UserHandler;
import com.example.webfluxdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.BodyExtractors.toMono;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.contentType;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class UserRouter {
    @Autowired
    UserRepository repository;
    @Bean
    public RouterFunction<ServerResponse> userRoute() {
        UserHandler userHandler = new UserHandler(repository);
        return RouterFunctions
            .route(GET("/user/{id}").and(accept(APPLICATION_JSON)), userHandler::getUser)
            .andRoute(GET("/user").and(accept(APPLICATION_JSON)), userHandler::listUser)
            .andRoute(POST("/user/create").and(contentType(APPLICATION_JSON)), userHandler::createUser);
        
    }
}