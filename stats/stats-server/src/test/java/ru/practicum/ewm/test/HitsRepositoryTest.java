package ru.practicum.ewm.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.practicum.model.HitsEntity;
import ru.practicum.repository.EndpointRepository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class HitsRepositoryTest {
    HitsEntity hits = HitsEntity.builder()
            .app("ewm").ip("11111").uri("/events/1").timestamp(LocalDateTime.now()).build();
    HitsEntity hits1 = HitsEntity.builder()
            .app("ewm").ip("11111").uri("/events/1").timestamp(LocalDateTime.now()).build();
    HitsEntity hits2 = HitsEntity.builder()
            .app("ewm").ip("12222").uri("/events/1").timestamp(LocalDateTime.now()).build();
    HitsEntity hits3 = HitsEntity.builder()
            .app("ewm").ip("12223").uri("/events/1").timestamp(LocalDateTime.now()).build();
    HitsEntity hits4 = HitsEntity.builder()
            .app("ewm").ip("12224").uri("/events/2").timestamp(LocalDateTime.now()).build();


    @Autowired
    private EndpointRepository repository;


    @BeforeEach
    void add() {
        repository.save(hits);
        repository.save(hits1);
        repository.save(hits2);
        repository.save(hits3);
        repository.save(hits4);
    }

   /* @AfterEach
    void deleteAll() {
        repository.deleteAll();
    }*/

    @Test
    void countHits() {
        List<HitsEntity> endpointEntities =
                repository.countHits(Arrays.asList("/events/1", "/events/2"), LocalDateTime.now().minusDays(1),LocalDateTime.now().plusDays(1));
        assertEquals(endpointEntities.size(),5);
        assertEquals(endpointEntities.get(0).getIp(),"11111");
    }

}
