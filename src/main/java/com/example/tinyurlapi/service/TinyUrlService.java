package com.example.tinyurlapi.service;

import com.example.tinyurlapi.dto.TinyUrlRequest;

public interface TinyUrlService {
    String getBaseUrl(String tinyUrl);
    String createTinyUrl(TinyUrlRequest request);
}
