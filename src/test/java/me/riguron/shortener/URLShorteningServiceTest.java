package me.riguron.shortener;

import me.riguron.shortener.domain.ShortenedUrl;
import me.riguron.shortener.repository.URLRepository;
import me.riguron.shortener.service.URLShorteningService;
import me.riguron.shortener.service.generator.URLShorteningGenerator;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class URLShorteningServiceTest {

    private static final String SHORTENING = "shortening";

    private URLShorteningService urlShorteningService;
    private URLRepository urlRepository;

    @Before
    public void setUp() {
        this.urlRepository = mock(URLRepository.class);
        this.urlShorteningService = new URLShorteningService(new URLShorteningGenerator(), urlRepository);
    }

    @Test
    public void whenGetExistingUrlWithNoUsesThenGotButNotUsed() {
        testGetOriginUrlInternal(false, 0);
    }

    @Test
    public void whenGetExistingUrlWithNoUsesThenGotAndUsed() {
        testGetOriginUrlInternal(true, 1);
    }

    private void testGetOriginUrlInternal(boolean trackVisit, int uses) {

        ShortenedUrl shortenedUrl = mock(ShortenedUrl.class);
        when(urlRepository.findById(SHORTENING)).thenReturn(Optional.of(shortenedUrl));
        Optional<ShortenedUrl> result = urlShorteningService.getOriginUrl(SHORTENING, trackVisit);

        assertTrue(result.isPresent());
        ShortenedUrl fetchedUrl = result.get();
        assertEquals(shortenedUrl, fetchedUrl);
        verify(fetchedUrl, times(uses)).use();
    }

    @Test
    public void shorten() {
    }
}