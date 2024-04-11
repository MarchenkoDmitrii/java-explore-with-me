package ru.practicum.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.DTO.EndpointModel;
import ru.practicum.DTO.EndpointResponse;
import ru.practicum.mapper.EndpointMapper;
import ru.practicum.repository.EndpointRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EndpointServiceImpl implements EndpointService {
    private final EndpointRepository endpointRepository;

    @Override
    public EndpointModel save(EndpointModel endpointModel) {
        return EndpointMapper.toModel(endpointRepository.save(EndpointMapper.toEntity(endpointModel)));
    }


    @Override
    public List<EndpointResponse> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, boolean unique) {
        List<EndpointResponse> stats;
        if (uris == null && !unique) {
            stats = endpointRepository.getAllStats(start, end);
        } else if (!unique) {
            stats = endpointRepository.getStatsWithUris(start, end, uris);
        } else if (uris == null) {
            stats = endpointRepository.getStatsWithUnique(start, end);
        } else {
            stats = endpointRepository.getStatsWithUniqueAndUris(start, end, uris);
        }

        return stats;
    }
}
