package ru.practicum.mapper;

import lombok.experimental.UtilityClass;
import ru.practicum.EndpointModel;
import ru.practicum.model.HitsEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class EndpointMapper {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public EndpointModel toModel(HitsEntity hitsEntity) {
        return EndpointModel.builder()
                .id(hitsEntity.getId())
                .uri(hitsEntity.getUri())
                .ip(hitsEntity.getIp())
                .app(hitsEntity.getApp())
                .timestamp(hitsEntity.getTimestamp().toString())
                .build();
    }

    public HitsEntity toEntity(EndpointModel endpointModel) {
        return HitsEntity.builder()
                .id(endpointModel.getId())
                .timestamp(LocalDateTime.parse(endpointModel.getTimestamp(), formatter))
                .uri(endpointModel.getUri())
                .ip(endpointModel.getIp())
                .app(endpointModel.getApp()).build();
    }

}
