package com.fabioqmarsiaj.springwebfluxessentials.service;

import com.fabioqmarsiaj.springwebfluxessentials.domain.Anime;
import com.fabioqmarsiaj.springwebfluxessentials.repository.AnimeRepository;
import com.fabioqmarsiaj.springwebfluxessentials.util.AnimeCreator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;
import reactor.blockhound.BlockHound;
import reactor.blockhound.BlockingOperationError;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

@ExtendWith(SpringExtension.class)
class AnimeServiceTest {

    private final Anime anime = AnimeCreator.createValidAnime();

    @InjectMocks
    private AnimeService service;
    @Mock
    private AnimeRepository repositoryMock;

    @BeforeAll
    public static void blockHoundSetup() {
        BlockHound.install();
    }

    @Test
    public void blockHoundWorks() {
        try {
            FutureTask<?> task = new FutureTask<>(() -> {
                Thread.sleep(0);
                return "";
            });
            Schedulers.parallel().schedule(task);

            task.get(10, TimeUnit.SECONDS);
            Assertions.fail("should fail");
        } catch (Exception e) {
            Assertions.assertTrue(e.getCause() instanceof BlockingOperationError);
        }
    }

    @BeforeEach
    public void setUp() {
        BDDMockito.when(repositoryMock.findAll())
                .thenReturn(Flux.just(anime));

        BDDMockito.when(repositoryMock.findById(ArgumentMatchers.anyInt()))
                .thenReturn(Mono.just(anime));
    }

    @Test
    @DisplayName("findAll returns a flux of anime")
    public void findAll_ReturnFluxOfAnime_WhenSuccessful() {
        StepVerifier.create(service.findAll())
                .expectSubscription()
                .expectNext(anime)
                .verifyComplete();
    }

    @Test
    @DisplayName("findById returns mono of anime when it exists")
    public void findById_ReturnMonoAnime_WhenSuccessful() {
        StepVerifier.create(service.findById(1))
                .expectSubscription()
                .expectNext(anime)
                .verifyComplete();
    }

    @Test
    @DisplayName("findById returns mono error when anime does not exists")
    public void findById_ReturnMonoError_WhenEmptyMonoIsReturned() {
        BDDMockito.when(repositoryMock.findById(ArgumentMatchers.anyInt()))
                .thenReturn(Mono.empty());

        StepVerifier.create(service.findById(1))
                .expectSubscription()
                .expectError(ResponseStatusException.class)
                .verify();
    }
}