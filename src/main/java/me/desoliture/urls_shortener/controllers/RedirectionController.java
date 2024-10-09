package me.desoliture.urls_shortener.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class RedirectionController {

    @GetMapping("/{alias}")
    public ResponseEntity<?> redirect(@PathVariable String alias) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
