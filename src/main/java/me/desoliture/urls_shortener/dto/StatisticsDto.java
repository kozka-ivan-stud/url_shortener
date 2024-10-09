package me.desoliture.urls_shortener.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StatisticsDto {
    private Long numberOfRedirects;
    private Long numberOfUniqueIps;
}
