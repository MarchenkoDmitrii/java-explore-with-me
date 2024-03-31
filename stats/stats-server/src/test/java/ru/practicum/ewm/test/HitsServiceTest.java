package ru.practicum.ewm.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.EndpointResponse;
import ru.practicum.mapper.EndpointMapper;
import ru.practicum.model.HitsEntity;
import ru.practicum.repository.EndpointRepository;
import ru.practicum.service.EndpointService;
import ru.practicum.service.EndpointServiceImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Formatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HitsServiceTest {

    @Mock
    private EndpointService service;

    @Mock
    private EndpointRepository repository;

    @InjectMocks
    private EndpointServiceImpl serviceImpl;

    @Test
    public void testGetStats(){
        String[] uris = {"uri1", "uri2"};
        HitsEntity hitsEntity1 = HitsEntity.builder()
                .app("app")
                .ip("111111")
                .uri("ur1")
                .timestamp(LocalDateTime.now()).build();

        HitsEntity hitsEntity2 = HitsEntity.builder()
                .app("app")
                .ip("111111")
                .uri("ur2")
                .timestamp(LocalDateTime.now()).build();

        HitsEntity hitsEntity3 = HitsEntity.builder()
                .app("app")
                .ip("111112")
                .uri("ur2")
                .timestamp(LocalDateTime.now()).build();

        when(repository.countHits(anyList(), any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(Arrays.asList(hitsEntity1, hitsEntity2, hitsEntity3));

        // Test unique=false
        EndpointResponse response1 = serviceImpl.getStats(
                LocalDateTime.now()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                LocalDateTime.now().plusDays(1)
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), uris, false);
        assertEquals(3, response1.getHits());


        // Test unique=true
        EndpointResponse response2 = serviceImpl.getStats(LocalDateTime.now()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                LocalDateTime.now().plusDays(1)
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), uris, true);
        assertEquals(2, response2.getHits());

    }
}
