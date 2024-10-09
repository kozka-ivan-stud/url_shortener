package me.desoliture.urls_shortener.services;

import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListSet;

@Service
public class StatisticsService {
    private final ConcurrentMap<String, Long> REDIRECTS = new ConcurrentHashMap<>();
    private final ConcurrentMap<String, ConcurrentSkipListSet<String>> IPS = new ConcurrentHashMap<>();

    public Long getRedirectsNumber(String alias) {
        return Optional.ofNullable(REDIRECTS.get(alias))
                .orElse(0L);
    }

    public Long getNumberOfUniqueIps(String alias) {
        return Optional.ofNullable(IPS.get(alias))
                .map(ConcurrentSkipListSet::size)
                .map(Long::valueOf)
                .orElse(0L);
    }

    public void updateStatistics(String alias, String ip) {
        REDIRECTS.compute(alias, (k, v) -> incrementNumberOfRedirects(v));
        IPS.compute(alias, (k, ips) -> putInIpsList(ips, ip));
    }

    public void deleteStatistics(String alias) {
        REDIRECTS.remove(alias);
        IPS.remove(alias);
    }

    private ConcurrentSkipListSet<String> putInIpsList(ConcurrentSkipListSet<String> ips, String ip) {
        var found = Objects.requireNonNullElse(ips, new ConcurrentSkipListSet<String>());
        found.add(ip);
        return found;
    }

    private static Long incrementNumberOfRedirects(Long v) {
        var found = Objects.requireNonNullElse(v, 0L);
        return found + 1L;
    }
}
