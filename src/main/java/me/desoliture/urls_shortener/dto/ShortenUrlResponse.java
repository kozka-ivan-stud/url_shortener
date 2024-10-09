package me.desoliture.urls_shortener.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
@AllArgsConstructor
public final class ShortenUrlResponse {
    private String shortenedAlias;
}
