package me.desoliture.urls_shortener.controllers;

import lombok.RequiredArgsConstructor;
import me.desoliture.urls_shortener.services.RedirectionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
@RequiredArgsConstructor
public class RedirectionController {
    private final RedirectionService redirectionService;

    @GetMapping("/{alias}")
    public ResponseEntity<Void> redirect(@PathVariable String alias) {
        return redirectionService.process(alias);
    }
}
