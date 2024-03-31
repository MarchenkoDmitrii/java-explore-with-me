package ru.practicum.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "hits", schema = "PUBLIC")
@AllArgsConstructor
@RequiredArgsConstructor
@Builder(toBuilder = true)
@ToString
public class HitsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "app")
    private String app;

    @Column(name = "uri")
    private String uri;

    @Column(name = "ip")
    private String ip;

    @CreationTimestamp
    @Column(name = "timestamp")
    private LocalDateTime timestamp;
}