package me.desoliture.urls_shortener.services;

import lombok.RequiredArgsConstructor;
import me.desoliture.urls_shortener.configuration.AuthConfiguration;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthorizationService {
    private final AuthConfiguration authConfiguration;

    public void tryAuthorize(String authToken) {
        if (!Objects.equals(authToken, authConfiguration.getToken())) {
            throw new IllegalArgumentException("Invalid auth token!");
        }
    }
}
