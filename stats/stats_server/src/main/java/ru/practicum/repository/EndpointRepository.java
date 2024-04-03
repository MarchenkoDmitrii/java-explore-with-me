package ru.practicum.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.DTO.EndpointResponse;
import ru.practicum.model.HitsEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface EndpointRepository extends JpaRepository<HitsEntity, Long> {
    @Query("SELECT new ru.practicum.DTO.EndpointResponse(h.app, h.uri, COUNT(h.ip)) " +
            "FROM HitsEntity h " +
            "WHERE h.timestamp BETWEEN ?1 AND ?2 " +
            "GROUP BY h.app, h.uri " +
            "ORDER BY COUNT(h) DESC")
    List<EndpointResponse> getAllStats(LocalDateTime start, LocalDateTime end);

    @Query("SELECT new ru.practicum.DTO.EndpointResponse(h.app, h.uri, COUNT(h.ip)) " +
            "FROM HitsEntity h " +
            "WHERE h.timestamp BETWEEN ?1 AND ?2 " +
            "AND h.uri IN ?3 " +
            "GROUP BY h.app, h.uri " +
            "ORDER BY COUNT(h) DESC")
    List<EndpointResponse> getStatsWithUris(LocalDateTime start, LocalDateTime end, List<String> uris);

    @Query("SELECT new ru.practicum.DTO.EndpointResponse(h.app, h.uri, COUNT(DISTINCT h.ip)) " +
            "FROM HitsEntity h " +
            "WHERE h.timestamp BETWEEN ?1 AND ?2 " +
            "GROUP BY h.app, h.uri " +
            "ORDER BY COUNT(DISTINCT h) DESC")
    List<EndpointResponse> getStatsWithUnique(LocalDateTime start, LocalDateTime end);

    @Query("SELECT new ru.practicum.DTO.EndpointResponse(h.app, h.uri, COUNT(DISTINCT h.ip)) " +
            "FROM HitsEntity h " +
            "WHERE h.timestamp BETWEEN ?1 AND ?2 " +
            "AND h.uri IN ?3 " +
            "GROUP BY h.app, h.uri " +
            "ORDER BY  COUNT(DISTINCT h) DESC")
    List<EndpointResponse> getStatsWithUniqueAndUris(LocalDateTime start, LocalDateTime end, List<String> uris);

}
