package com.fabioqmarsiaj.springwebfluxessentials.repository;

import com.fabioqmarsiaj.springwebfluxessentials.domain.Anime;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface AnimeRepository extends ReactiveCrudRepository<Anime, Integer> {

    Mono<Anime> findBy(int id);
}
