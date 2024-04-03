package ru.practicum.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.practicum.DTO.EndpointModel;
import ru.practicum.DTO.EndpointResponse;
import ru.practicum.service.EndpointService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class StatController {
    private final EndpointService endpointService;

    @PostMapping("/hit")
    public EndpointModel create(@RequestBody EndpointModel endpointHit) {
        return endpointService.save(endpointHit);
    }

    @GetMapping("/stats")
    public List<EndpointResponse> getStats(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime start,
                                           @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime end,
                                           @RequestParam(required = false) List<String> uris,
                                           @RequestParam(defaultValue = "false") Boolean unique) {
        return endpointService.getStats(start, end, uris, unique);
    }
}