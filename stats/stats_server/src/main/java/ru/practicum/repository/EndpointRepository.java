package ru.practicum.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.practicum.DTO.EndpointResponse;
import ru.practicum.model.HitsEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface EndpointRepository extends JpaRepository<HitsEntity, Long> {
    @Query("SELECT new ru.practicum.DTO.EndpointResponse(h.app, h.uri, COUNT(h.ip)) " +
            "FROM HitsEntity h " +
            "WHERE h.timestamp BETWEEN :start AND :end " +
            "GROUP BY h.app, h.uri " +
            "ORDER BY COUNT(h) DESC")
    List<EndpointResponse> getAllStats(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query("SELECT new ru.practicum.DTO.EndpointResponse(h.app, h.uri, COUNT(h.ip)) " +
            "FROM HitsEntity h " +
            "WHERE h.timestamp BETWEEN :start AND :end " +
            "AND h.uri IN :uris " +
            "GROUP BY h.app, h.uri " +
            "ORDER BY COUNT(h) DESC")
    List<EndpointResponse> getStatsWithUris(@Param("start") LocalDateTime start,
                                            @Param("end") LocalDateTime end,
                                            @Param("uris") List<String> uris);

    @Query("SELECT new ru.practicum.DTO.EndpointResponse(h.app, h.uri, COUNT(DISTINCT h.ip)) " +
            "FROM HitsEntity h " +
            "WHERE h.timestamp BETWEEN :start AND :end " +
            "GROUP BY h.app, h.uri " +
            "ORDER BY COUNT(DISTINCT h) DESC")
    List<EndpointResponse> getStatsWithUnique(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query("SELECT new ru.practicum.DTO.EndpointResponse(h.app, h.uri, COUNT(DISTINCT h.ip)) " +
            "FROM HitsEntity h " +
            "WHERE h.timestamp BETWEEN :start AND :end " +
            "AND h.uri IN :uris " +
            "GROUP BY h.app, h.uri " +
            "ORDER BY  COUNT(DISTINCT h) DESC")
    List<EndpointResponse> getStatsWithUniqueAndUris(@Param("start") LocalDateTime start,
                                                     @Param("end") LocalDateTime end,
                                                     @Param("uris") List<String> uris);

}
