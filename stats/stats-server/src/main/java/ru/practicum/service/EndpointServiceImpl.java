package ru.practicum.service;

import org.springframework.stereotype.Service;
import ru.practicum.EndpointModel;
import ru.practicum.EndpointResponse;
import ru.practicum.mapper.EndpointMapper;
import ru.practicum.model.HitsEntity;
import ru.practicum.repository.EndpointRepository;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Service
public class EndpointServiceImpl implements EndpointService {
    EndpointRepository endpointRepository;

    private static DateTimeFormatter formatter() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }

    private static String encodeString(String input) {
        // Кодирование строки времени
        return URLEncoder.encode(input, StandardCharsets.UTF_8);
    }

    @Override
    public void save(EndpointModel endpointModel) {
        endpointRepository.save(EndpointMapper.toEntity(endpointModel));
    }

    @Override
    public List<EndpointModel> getAllHits() {
        return null;
    }

    @Override
    public EndpointResponse getStats(String startDecoded, String endDecoded, String[] uris, boolean unique) {
        LocalDateTime start = LocalDateTime.parse(startDecoded, formatter());
        LocalDateTime end = LocalDateTime.parse(endDecoded, formatter());
        List<String> uri = Arrays.stream(uris).toList();
        List<HitsEntity> hits = endpointRepository.countHits(uri, start, end);
        String app = hits.stream()
                .map(HitsEntity::getApp)
                .toString();
        long count;
        if (!unique) {
            count = hits.stream().map(HitsEntity::getIp).count();
        } else {
            count = hits.stream().map(HitsEntity::getIp).distinct().count();
        }
        return EndpointResponse.builder()
                .hits(count)
                .uri(uris)
                .app(app)
                .build();

    }
}
