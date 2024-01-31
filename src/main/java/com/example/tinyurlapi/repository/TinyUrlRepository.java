package com.example.tinyurlapi.repository;

import com.example.tinyurlapi.model.TinyUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TinyUrlRepository extends JpaRepository<TinyUrl, Long> {
    Optional<TinyUrl> findByTinyUrlValue(String tinyUrlValue);
}
