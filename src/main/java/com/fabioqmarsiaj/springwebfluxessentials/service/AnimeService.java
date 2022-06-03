package com.fabioqmarsiaj.springwebfluxessentials.service;

import com.fabioqmarsiaj.springwebfluxessentials.domain.Anime;
import com.fabioqmarsiaj.springwebfluxessentials.repository.AnimeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@Slf4j
@RequiredArgsConstructor
public class AnimeService {

    private final AnimeRepository repository;

    public Flux<Anime> findAll() {
        return repository.findAll();
    }
}
