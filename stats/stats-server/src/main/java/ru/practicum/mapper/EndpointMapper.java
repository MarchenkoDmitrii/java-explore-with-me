package ru.practicum.mapper;


import ru.practicum.EndpointModel;
import ru.practicum.EndpointRequest;
import ru.practicum.EndpointResponse;
import ru.practicum.model.HitsEntity;

import java.util.List;

public class EndpointMapper {
    public static EndpointModel toModel(HitsEntity hitsEntity) {
        return EndpointModel.builder()
                .app(hitsEntity.getApp())
                .id(hitsEntity.getId()).ip(hitsEntity.getIp())
                .timestamp(hitsEntity.getTimestamp())
                .uri(hitsEntity.getUri())
                .build();
    }

    public static EndpointRequest toRequest(EndpointModel endpointModel) {
       return EndpointRequest.builder()
                .app(endpointModel.getApp())
                .ip(endpointModel.getIp())
                .uri(endpointModel.getUri())
                .timestamp(endpointModel.getTimestamp())
                .build();
    }

    public static EndpointResponse toResponse(List<HitsEntity> endpointModel, String[] uris, long hits) {
        EndpointResponse response = EndpointResponse.builder().app(endpointModel.stream()
                .map(HitsEntity::getApp)
                .toString()).build();
        response.setUri(uris);
        response.setHits(hits);
        return response;
    }

    public static HitsEntity toEntity(EndpointModel endpointModel) {
        return  HitsEntity.builder()
                .app(endpointModel.getApp())
                .id(endpointModel.getId())
                .uri(endpointModel.getUri())
                .timestamp(endpointModel.getTimestamp())
                .build();
    }

}
