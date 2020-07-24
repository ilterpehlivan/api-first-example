package com.ilt.api;

import com.ilt.api.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient(timeout = "10000")
public class IntegrationTest {

  @LocalServerPort
  int port;



  @Test
  public void shouldGetUserWithGivenIdSuccesfully() {
    String baseUrl = String.format("http://localhost:%s", port);

    //First create user

    User dummyUser = new User();
    dummyUser.setUsername("dummy");
    dummyUser.setEmail("dummy@dummy");

    WebTestClient
        .bindToServer()
        .baseUrl(baseUrl)
        .build()
        .post()
        .uri("/v2/user")
        .accept(MediaType.APPLICATION_JSON)
        .body(Mono.just(dummyUser), User.class)
        .exchange()
        // Then
        .expectStatus()
        .isOk();

    //then get it
    WebTestClient
        .bindToServer()
        .baseUrl(baseUrl)
        .build()
        .get()
        .uri("/v2/user/dummy")
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        // Then
        .expectStatus()
        .isOk()
        .expectBody();
  }

  @Test
  void ping() {
    String baseUrl = String.format("http://localhost:%s", port);
    WebTestClient
        .bindToServer()
        .baseUrl(baseUrl)
        .build()
        .get()
        .uri("/example/ping")
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        // Then
        .expectStatus()
        .isOk()
        .expectBody();
  }
}
