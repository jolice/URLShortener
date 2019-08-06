package me.riguron.shortener.service;

import me.riguron.shortener.domain.Account;
import me.riguron.shortener.domain.ShortenedUrl;
import me.riguron.shortener.repository.URLRepository;
import me.riguron.shortener.service.generator.URLShorteningGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Component
public class URLShorteningService {

    @Value("${url.shortening.length}")
    private int shortUrlLength;

    private URLShorteningGenerator urlShorteningGenerator;

    private URLRepository urlRepository;

    @Autowired
    public URLShorteningService(URLShorteningGenerator urlShorteningGenerator, URLRepository urlRepository) {
        this.urlShorteningGenerator = urlShorteningGenerator;
        this.urlRepository = urlRepository;
    }


    @Transactional
    public Optional<ShortenedUrl> getOriginUrl(String shortening, boolean trackVisit) {
        return urlRepository.findById(shortening).map(shortenedUrl -> {
            if (trackVisit) {
                shortenedUrl.use();
            }
            return shortenedUrl;
        });
    }


    @Transactional
    public String shorten(Account account, String url, int redirectType) {
        String shortening;
        do {
            shortening = urlShorteningGenerator.generateShortUrl(shortUrlLength);
        } while (urlRepository.existsById(shortening));
        ShortenedUrl shortenedUrl = new ShortenedUrl(shortening, url, redirectType);
        account.getShortenedUrls().add(shortenedUrl);
        return shortening;
    }


}
