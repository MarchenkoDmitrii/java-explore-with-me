package ru.practicum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class EndpointModel {
    private int id;
    private String app;
    private String uri;
    private String ip;
    private String timestamp;
}