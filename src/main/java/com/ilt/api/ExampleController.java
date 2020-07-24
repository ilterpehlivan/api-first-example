package com.ilt.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/example")
public class ExampleController {

  @GetMapping("/ping")
  Mono<String> getDummy(){
    return Mono.just("dummyy");
  }

}
