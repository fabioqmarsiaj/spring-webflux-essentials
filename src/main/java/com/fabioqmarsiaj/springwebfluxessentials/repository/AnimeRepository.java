package com.fabioqmarsiaj.springwebfluxessentials.repository;

import com.fabioqmarsiaj.springwebfluxessentials.domain.Anime;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimeRepository extends ReactiveCrudRepository<Anime, Integer> {
}
