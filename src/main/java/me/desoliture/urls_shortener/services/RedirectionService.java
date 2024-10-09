package me.desoliture.urls_shortener.services;

import lombok.RequiredArgsConstructor;
import me.desoliture.urls_shortener.utils.IpExtractor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedirectionService {
    private final UrlsService urlsService;
    private final StatisticsService statisticsService;

    public ResponseEntity<Void> process(String alias) {
        var originalUrl = urlsService.get(alias);
        updateStatistics(alias);
        return ResponseEntity.status(HttpStatus.FOUND)
                .header(HttpHeaders.LOCATION, originalUrl)
                .build();
    }

    private void updateStatistics(String alias) {
        var resolvedIp = IpExtractor.extractIpFromCurrentRequest();
        statisticsService.updateStatistics(alias, resolvedIp);
    }
}
