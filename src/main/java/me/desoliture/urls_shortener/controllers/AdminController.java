package me.desoliture.urls_shortener.controllers;

import lombok.RequiredArgsConstructor;
import me.desoliture.urls_shortener.dto.ShortenUrlRequest;
import me.desoliture.urls_shortener.dto.UrlInfoDto;
import me.desoliture.urls_shortener.services.AdminService;
import me.desoliture.urls_shortener.services.AuthorizationService;
import me.desoliture.urls_shortener.utils.UriUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AuthorizationService authorizationService;
    private final AdminService adminService;

    @PostMapping("/shorten")
    public ResponseEntity<UrlInfoDto> shortenUrl(@RequestBody ShortenUrlRequest request,
                                                 @RequestHeader(AUTHORIZATION) String authorization) {
        authorizationService.tryAuthorize(authorization);
        var created = adminService.create(request.getOriginalUrl());
        var location = UriUtils.buildUriToCreated(created.getAlias());
        return ResponseEntity.created(location)
                .body(created);
    }

    @GetMapping("/{alias}")
    public ResponseEntity<UrlInfoDto> getInfo(@PathVariable String alias,
                                              @RequestHeader(AUTHORIZATION) String authorization) {
        authorizationService.tryAuthorize(authorization);
        var found = adminService.get(alias);
        return ResponseEntity.ok(found);
    }

    @DeleteMapping("/{alias}")
    public ResponseEntity<Void> delete(@PathVariable String alias,
                                       @RequestHeader(AUTHORIZATION) String authorization) {
        authorizationService.tryAuthorize(authorization);
        adminService.delete(alias);
        return ResponseEntity.noContent().build();
    }
}
