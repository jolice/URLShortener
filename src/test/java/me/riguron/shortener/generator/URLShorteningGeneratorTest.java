package me.riguron.shortener.generator;

import me.riguron.shortener.service.generator.URLShorteningGenerator;
import org.junit.Test;

import static org.junit.Assert.*;

public class URLShorteningGeneratorTest {

    @Test
    public void whenGenerateUrlOfFixedLengthThenReturnedUrlWithSameLength() {
        URLShorteningGenerator urlShorteningGenerator = new URLShorteningGenerator();
        String generatedUrl = urlShorteningGenerator.generateShortUrl(5);
        assertNotNull(generatedUrl);
        assertEquals(generatedUrl.length(), 5);
    }

    @Test
    public void whenGenerateUrlThenNoNumbers() {
        URLShorteningGenerator urlShorteningGenerator = new URLShorteningGenerator();
        String generatedUrl = urlShorteningGenerator.generateShortUrl(5);
        assertTrue(generatedUrl.chars().allMatch(Character::isLetter));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNegativeLengthThenFailed() {
        new URLShorteningGenerator().generateShortUrl(-1);
    }


    @Test(expected = IllegalArgumentException.class)
    public void whenZeroLengthThenFailed() {
        new URLShorteningGenerator().generateShortUrl(0);
    }
}