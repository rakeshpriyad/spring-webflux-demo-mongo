package com.example.webfluxdemo;

import com.example.webfluxdemo.model.Employee;
import com.example.webfluxdemo.repository.EmpRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebfluxDemoApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Autowired
    EmpRepository empRepository;

	@Test
	public void testCreateTweet() {
		Employee emp = new Employee("Test");

		webTestClient.post().uri("/emps")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(emp), Employee.class)
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
				.expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.text").isEqualTo("This is a Test Tweet");
	}

	@Test
    public void testGetAllTweets() {
	    webTestClient.get().uri("/emps")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBodyList(Employee.class);
    }

    @Test
    public void testGetSingleTweet() {
        Employee emp = empRepository.save(new Employee("Test")).block();

        webTestClient.get()
                .uri("/emps/{id}", Collections.singletonMap("id", emp.getId()))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(response ->
                        Assertions.assertThat(response.getResponseBody()).isNotNull());
    }

    @Test
    public void testUpdateTweet() {
        Employee emp = empRepository.save(new Employee("Name")).block();

        Employee newEmp = new Employee("Updated Emp");

        webTestClient.put()
                .uri("/emps/{id}", Collections.singletonMap("id", emp.getId()))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(newEmp), Employee.class)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody()
                .jsonPath("$.text").isEqualTo("Updated Tweet");
    }

    @Test
    public void testDeleteTweet() {
	    Employee emp = empRepository.save(new Employee("To be deleted")).block();

	    webTestClient.delete()
                .uri("/emps/{id}", Collections.singletonMap("id",  emp
                        .getId()))
                .exchange()
                .expectStatus().isOk();
    }
}
