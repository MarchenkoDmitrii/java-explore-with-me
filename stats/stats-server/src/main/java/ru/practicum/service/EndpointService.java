package ru.practicum.service;

import org.springframework.stereotype.Service;

import ru.practicum.EndpointModel;

import ru.practicum.EndpointResponse;

import java.util.List;

public interface EndpointService {
    void save(EndpointModel endpointModel);

    List<EndpointModel> getAllHits();

    EndpointResponse getStats(String startDecoded, String endDecoded, String[] uris, boolean unique);
}
