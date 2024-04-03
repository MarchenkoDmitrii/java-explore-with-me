package ru.practicum.service;


import ru.practicum.DTO.EndpointModel;
import ru.practicum.DTO.EndpointResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface EndpointService {
    EndpointModel save(EndpointModel endpointModel);

    List<EndpointResponse> getStats(LocalDateTime startDecoded, LocalDateTime endDecoded, List<String> uris, boolean unique);

}
