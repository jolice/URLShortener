package me.riguron.shortener.web;

import me.riguron.shortener.domain.ShortenedUrl;
import me.riguron.shortener.service.AccountService;
import me.riguron.shortener.service.URLShorteningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class StatisticController {

    private URLShorteningService service;

    @Autowired
    public StatisticController(URLShorteningService service) {
        this.service = service;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/stats", consumes = "application/json", produces = "application/json")
    public Map<String, Integer> statistics(Principal principal) {
        return service.getShorteningsFor(principal.getName())
                .stream()
                .collect(Collectors.toMap(ShortenedUrl::getUrl, ShortenedUrl::getUses));
    }
}
