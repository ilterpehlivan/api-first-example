package com.ilt.api.impl;

import com.ilt.api.model.ModelApiResponse;
import com.ilt.api.model.Pet;
import com.ilt.api.rest.interfaces.PetApiDelegate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class PetInterfaceImpl implements PetApiDelegate {

  Map<Long, Pet> pets = new ConcurrentHashMap<>();

  @Override
  public Mono<Void> addPet(Mono<Pet> body, ServerWebExchange exchange) {
    return body
        .map(pet -> {
          log.info("inside the addPet service with request {}", pet);
          pets.put(pet.getId(), pet);
          exchange.getResponse().setStatusCode(HttpStatus.CREATED);
          return pet;
        })
        .then();
  }

  @Override
  public Mono<Void> deletePet(Long petId, String apiKey, ServerWebExchange exchange) {
    return null;
  }

  @Override
  public Mono<Flux<Pet>> findPetsByStatus(List<String> status, ServerWebExchange exchange) {
    return null;
  }

  @Override
  public Mono<Flux<Pet>> findPetsByTags(List<String> tags, ServerWebExchange exchange) {
    return null;
  }

  @Override
  public Mono<Pet> getPetById(Long petId, ServerWebExchange exchange) {
    return Mono.just(pets.get(petId)).doOnNext(p->log.info("returning pet {} by id {} ",p,petId));
  }

  @Override
  public Mono<Void> updatePet(Mono<Pet> body, ServerWebExchange exchange) {
    return null;
  }

  @Override
  public Mono<Void> updatePetWithForm(Long petId, String name, String status,
      ServerWebExchange exchange) {
    return null;
  }

  @Override
  public Mono<ModelApiResponse> uploadFile(Long petId, String additionalMetadata,
      MultipartFile file, ServerWebExchange exchange) {
    return null;
  }
}
