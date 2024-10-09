package me.desoliture.urls_shortener.controllers;

import me.desoliture.urls_shortener.dto.ShortenUrlRequest;
import me.desoliture.urls_shortener.dto.ShortenUrlResponse;
import me.desoliture.urls_shortener.dto.UrlInfoDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @PostMapping("/shorten")
    public ResponseEntity<ShortenUrlResponse> shortenUrl(@RequestBody ShortenUrlRequest request,
                                                         @RequestHeader(AUTHORIZATION) String authorization) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @GetMapping("/{alias}")
    public ResponseEntity<UrlInfoDto> getInfo(@PathVariable String alias,
                                              @RequestHeader(AUTHORIZATION) String authorization) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @DeleteMapping("/{alias}")
    public ResponseEntity<Void> delete(@PathVariable String alias,
                                       @RequestHeader(AUTHORIZATION) String authorization) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
