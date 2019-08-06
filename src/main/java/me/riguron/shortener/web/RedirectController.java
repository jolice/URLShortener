package me.riguron.shortener.web;

import me.riguron.shortener.domain.ShortenedUrl;
import me.riguron.shortener.service.URLShorteningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;


@Controller
public class RedirectController {

    private URLShorteningService urlShorteningService;

    @Autowired
    public RedirectController(URLShorteningService urlShorteningService) {
        this.urlShorteningService = urlShorteningService;
    }

    @GetMapping("/{target}")
    public void doRedirect(@PathVariable String target, HttpServletResponse response) {
        Optional<ShortenedUrl> optionalTarget = urlShorteningService.getOriginUrl(target, true);
        if (optionalTarget.isPresent()) {
            ShortenedUrl shortenedUrl = optionalTarget.get();
            response.setHeader("Location", shortenedUrl.getUrl());
            response.setStatus(shortenedUrl.getRedirectType());
        } else {
            response.setStatus(404);
        }


    }
}
