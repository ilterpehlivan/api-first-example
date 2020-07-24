package com.ilt.api.impl;

import com.ilt.api.model.User;
import com.ilt.api.rest.interfaces.UserProfileApiDelegate;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class UserInterfaceImpl implements UserProfileApiDelegate {

  Map<String, User> userMap = new ConcurrentHashMap();

  @Override
  public Mono<Void> createUser(Mono<User> body, ServerWebExchange exchange) {
    log.info("Inside createUser");
    return body
        .doOnNext(u -> log.info("creating user {}", u.getUsername()))
        .flatMap(user -> {
          if (userMap.get(user.getUsername()) != null) {
            log.error("user already exist");
            exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
            return Mono.empty();
          }
          userMap.put(user.getUsername(), user);
          return Mono.empty();
        }).then();
  }

  @Override
  public Mono<User> getUserByName(String username, ServerWebExchange exchange) {
    log.info("getting user {} from mem {}",username,userMap);
    return Mono.just(userMap.get(username));
  }
}
