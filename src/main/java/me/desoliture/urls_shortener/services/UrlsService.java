package me.desoliture.urls_shortener.services;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
@RequiredArgsConstructor
public class UrlsService {
    private final ConcurrentMap<String, String> ORIGINAL_URLS = new ConcurrentHashMap<>();

    public String create(String originalUrl) {
        validateUrl(originalUrl);
        var alias = RandomStringUtils.secure().nextAlphabetic(4, 9);
        ORIGINAL_URLS.put(alias, originalUrl);
        return alias;
    }

    public String get(String alias) {
        return getOriginalUrl(alias);
    }

    public void delete(String alias) {
        getOriginalUrl(alias);
        ORIGINAL_URLS.remove(alias);
    }

    private String getOriginalUrl(String alias) {
        return Optional.ofNullable(ORIGINAL_URLS.get(alias))
                .orElseThrow(() -> new NoSuchElementException("No info found for alias: " + alias));
    }

    private void validateUrl(String originalUrl) {
        if (!originalUrl.startsWith("http") && !originalUrl.startsWith("https")) {
            throw new IllegalArgumentException("Invalid URL: " + originalUrl);
        }
    }
}
