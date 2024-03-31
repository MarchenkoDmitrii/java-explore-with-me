package ru.practicum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class EndpointFilterModel {
    private String search;

    private String uri;

    private LocalDateTime start;

    private LocalDateTime end;

    private boolean unique;
}
