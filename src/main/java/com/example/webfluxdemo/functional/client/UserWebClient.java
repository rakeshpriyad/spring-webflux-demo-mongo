package com.example.webfluxdemo.functional.client;

import java.util.List;

import com.example.webfluxdemo.model.User;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class UserWebClient {
    private WebClient client = WebClient.create("http://localhost:8080");
    // For getting all users
    private Mono<ClientResponse> result = client.get()
            .uri("/user")
            .accept(MediaType.APPLICATION_JSON_UTF8)
            .exchange();
    
    // Getting user by ID
    private Mono<User> singleUser = client.get()
            .uri("/user/1")
            .accept(MediaType.APPLICATION_JSON_UTF8)
            .exchange()
            .flatMap(res -> res.bodyToMono(User.class));
    

    public List<User> getResult() {
            Flux<User> userList = result.flatMapMany(res -> res.bodyToFlux(User.class));
            return userList.collectList().block();
    }

    public static void main(String[] args) {
        UserWebClient uc = new UserWebClient();
        System.out.println(uc.result);
    }
}