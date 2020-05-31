package com.example.webfluxdemo.functional.handler;

import com.example.webfluxdemo.model.User;
import com.example.webfluxdemo.repository.UserRepository;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class UserHandler {
    private final UserRepository userRepository;

    public UserHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public Mono<ServerResponse> listUser(ServerRequest request) {
        Flux<User> user = userRepository.findAll();
        return ServerResponse.ok().contentType(APPLICATION_JSON).body(user, User.class);
    }
    
    public Mono<ServerResponse> getUser(ServerRequest request) {
        String userId = request.pathVariable("id");
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        Mono<User> userMono = userRepository.findById(userId);
        return userMono.flatMap(user -> ServerResponse.ok()
              .contentType(APPLICATION_JSON)
              .body(BodyInserters.fromObject(user)))
              .switchIfEmpty(notFound);
    }
    
    public Mono<ServerResponse> createUser(ServerRequest request) {
        System.out.println("in create user");
        Mono<User> user = request.bodyToMono(User.class);

        return ServerResponse.ok().build(user.flatMap(e -> {
            userRepository.save(e).subscribe();
            return Mono.empty();
        }));
    }
}