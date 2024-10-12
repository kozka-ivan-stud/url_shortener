package me.desoliture.urls_shortener.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public final class SystemErrorResponse {
    private String errorMessage;
    private HttpStatus httpStatus;
}
