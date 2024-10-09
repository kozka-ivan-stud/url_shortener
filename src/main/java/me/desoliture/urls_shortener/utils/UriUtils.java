package me.desoliture.urls_shortener.utils;

import lombok.experimental.UtilityClass;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@UtilityClass
public class UriUtils {

    public URI buildUriToCreated(String alias) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{alias}")
                .buildAndExpand(alias)
                .toUri();
    }
}
