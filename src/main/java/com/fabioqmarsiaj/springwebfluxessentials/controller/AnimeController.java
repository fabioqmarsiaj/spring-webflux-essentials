package com.fabioqmarsiaj.springwebfluxessentials.controller;

import com.fabioqmarsiaj.springwebfluxessentials.domain.Anime;
import com.fabioqmarsiaj.springwebfluxessentials.service.AnimeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("animes")
@Slf4j
@RequiredArgsConstructor
public class AnimeController {

    private final AnimeService service;

    @GetMapping
    public Flux<Anime> listAll() {
        return service.findAll();
    }

    @GetMapping(path = "{id}")
    public Mono<Anime> findById(@PathVariable int id) {
        return service.findById(id);
    }
}
