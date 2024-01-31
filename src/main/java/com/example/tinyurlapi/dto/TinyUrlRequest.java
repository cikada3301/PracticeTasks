package com.example.tinyurlapi.dto;

import lombok.Data;

import java.time.Duration;

@Data
public class TinyUrlRequest {
    private String baseUrl;
    private String alias;
    private Duration ttl;
}
