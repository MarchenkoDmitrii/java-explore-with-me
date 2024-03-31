package ru.practicum.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.model.HitsEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface EndpointRepository extends JpaRepository<HitsEntity, Long> {
    @Query("select e from HitsEntity e " +
            "where e.timestamp between ?2 and ?3 " +
            "and e.uri in ?1 "
    )
    List<HitsEntity> countHits(List<String> uri, LocalDateTime start, LocalDateTime end);

}
