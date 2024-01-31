package com.example.tinyurlapi;

import com.example.tinyurlapi.controller.TinyUrlController;
import com.example.tinyurlapi.dto.TinyUrlRequest;
import com.example.tinyurlapi.service.TinyUrlService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Duration;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TinyUrlController.class)
class TinyUrlApiApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TinyUrlService tinyUrlService;

    @Test
    void testCreate() throws Exception {
        String baseUrl = "https://www.example.com";
        String shortUrl = "abc123";
        String alias = "example";
        Duration ttl = Duration.ofDays(7);

        TinyUrlRequest urlRequest = new TinyUrlRequest();
        urlRequest.setBaseUrl(baseUrl);
        urlRequest.setAlias(alias);
        urlRequest.setTtl(ttl);

        when(tinyUrlService.createTinyUrl(any())).thenReturn(shortUrl);

        mockMvc.perform(post("/api/v1/tinyurls")
                        .contentType("application/json")
                        .content("{\"longUrl\":\"" + baseUrl + "\",\"alias\":\"" + alias + "\",\"ttl\":\"P7D\"}")
                )
                .andExpect(status().isOk())
                .andExpect(content().string(shortUrl));

    }

    @Test
    void testGetBaseUrl() throws Exception {
        String shortUrl = "abc123";
        String longUrl = "https://www.example.com";

        TinyUrlRequest urlRequest = new TinyUrlRequest();

        when(tinyUrlService.getBaseUrl(shortUrl)).thenReturn(longUrl);

        mockMvc.perform(get("/api/v1/tinyurls/" + shortUrl))
                .andExpect(status().isOk())
                .andExpect(content().string(longUrl));
    }

}
