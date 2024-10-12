package me.desoliture.urls_shortener.configuration;

public class BadCredentialsSystemException extends RuntimeException {
    public BadCredentialsSystemException(String message) {
        super(message);
    }
}
