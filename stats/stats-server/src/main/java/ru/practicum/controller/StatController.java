package ru.practicum.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.EndpointModel;
import ru.practicum.EndpointResponse;
import ru.practicum.service.EndpointService;


import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class StatController {
    private final EndpointService endpointService;

    @PostMapping("/hit")
    public ResponseEntity<String> postHit(@Valid @RequestBody EndpointModel hitDto) {
        log.info("Statistic service: сохранен запрос для эндпоинта {}", hitDto.getUri());
        endpointService.save(hitDto);
        return new ResponseEntity<>("Информация сохранена", HttpStatus.CREATED);
    }

    @GetMapping("/stats")
    public ResponseEntity<EndpointResponse> getStats(@RequestParam(name = "start") String start,
                                           @RequestParam(name = "end") String end,
                                           @RequestParam(name = "uris", required = false) String[] uris,
                                           @RequestParam(name = "unique", defaultValue = "false") boolean unique) {
        log.info("Statistic service: запрошена статистика для эндпоинтов {}", uris);
        return ResponseEntity.ok(endpointService.getStats(start, end, uris, unique));
    }

    @GetMapping("/hits")
    public List<EndpointModel> getAllHits() {
        log.info("Statistic service: запрошена вся статистика"); //логируем
        return endpointService.getAllHits();
    }
}