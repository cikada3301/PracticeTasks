package com.example.tinyurlapi.controller;

import com.example.tinyurlapi.dto.TinyUrlRequest;
import com.example.tinyurlapi.service.TinyUrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tinyurls")
@RequiredArgsConstructor
public class TinyUrlController {

    private final TinyUrlService tinyUrlService;

    @PostMapping
    public String createTinyUrl(@RequestBody TinyUrlRequest tinyUrlRequest) {
        return tinyUrlService.createTinyUrl(tinyUrlRequest);
    }

    @GetMapping("/{tinyUrlValue}")
    public String getBaseUrl(@PathVariable String tinyUrlValue) {
        return tinyUrlService.getBaseUrl(tinyUrlValue);
    }

}
