package ru.practicum.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
public class EndpointResponse {
    private String app;
    private String uri;
    private Long hits;
}