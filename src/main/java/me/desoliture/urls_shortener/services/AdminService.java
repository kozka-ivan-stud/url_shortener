package me.desoliture.urls_shortener.services;

import lombok.RequiredArgsConstructor;
import me.desoliture.urls_shortener.dto.StatisticsDto;
import me.desoliture.urls_shortener.dto.UrlInfoDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final UrlsService urlsService;
    private final StatisticsService statisticsService;

    public UrlInfoDto get(String alias) {
        var originalUrl = urlsService.get(alias);
        var redirectsNumber = statisticsService.getRedirectsNumber(alias);
        var uniqueIpsNumber = statisticsService.getNumberOfUniqueIps(alias);
        var statistics = new StatisticsDto(redirectsNumber, uniqueIpsNumber);
        return new UrlInfoDto(originalUrl, alias, statistics);
    }

    public UrlInfoDto create(String originalUrl) {
        var alias = urlsService.create(originalUrl);
        return get(alias);
    }

    public void delete(String alias) {
        urlsService.delete(alias);
        statisticsService.deleteStatistics(alias);
    }
}
