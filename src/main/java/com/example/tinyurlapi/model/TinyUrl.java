package com.example.tinyurlapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tiny_urls")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TinyUrl {
    @ToString.Exclude
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "base_url", nullable = false)
    private String baseUrl;

    @Column(name = "tiny_url_value", nullable = false)
    private String tinyUrlValue;

    @Column(name = "expiration_date_time")
    private LocalDateTime expirationDateTime;
}
