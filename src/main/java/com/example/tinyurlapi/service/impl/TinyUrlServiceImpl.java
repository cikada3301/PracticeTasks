package com.example.tinyurlapi.service.impl;

import com.example.tinyurlapi.dto.TinyUrlRequest;
import com.example.tinyurlapi.model.TinyUrl;
import com.example.tinyurlapi.repository.TinyUrlRepository;
import com.example.tinyurlapi.service.TinyUrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TinyUrlServiceImpl implements TinyUrlService {

    private final TinyUrlRepository tinyUrlRepository;

    @Override
    @Transactional
    public String getBaseUrl(String tinyUrlValue) {
        TinyUrl tinyUrl = tinyUrlRepository.findByTinyUrlValue(tinyUrlValue).orElseThrow(() -> new RuntimeException("Invalid url"));

        if (tinyUrl.getExpirationDateTime() != null) {
            if (tinyUrl.getExpirationDateTime().isAfter(LocalDateTime.now())) {
                tinyUrlRepository.delete(tinyUrl);
                return "404";
            }

            return tinyUrl.getBaseUrl();
        }

        return tinyUrl.getBaseUrl();
    }

    @Override
    @Transactional
    public String createTinyUrl(TinyUrlRequest request) {
        TinyUrl tinyUrl = new TinyUrl();

        tinyUrl.setBaseUrl(request.getBaseUrl());

        String tinyUrlValue = generateShortUrl();

        String alias = request.getAlias();

        if (alias != null) {
            tinyUrlValue = request.getAlias();
        }

        if (request.getTtl() != null) {
            tinyUrl.setExpirationDateTime(
                    LocalDateTime.now().plus(request.getTtl())
            );
        }

        tinyUrl.setTinyUrlValue(tinyUrlValue);

        return tinyUrlRepository.save(tinyUrl).getTinyUrlValue();
    }

    private String generateShortUrl() {
        String allowedCharacters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int length = 6;
        StringBuilder shortUrl = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomIndex = (int) (Math.random() * allowedCharacters.length());
            shortUrl.append(allowedCharacters.charAt(randomIndex));
        }
        return shortUrl.toString();
    }
}
