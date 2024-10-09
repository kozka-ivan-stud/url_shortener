package me.desoliture.urls_shortener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication
public class UrlsShortenerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UrlsShortenerApplication.class, args);
    }

}
