package me.riguron.shortener.service.generator;

import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;


@Component
public class URLShorteningGenerator {


    public String generateShortUrl(int length) {

        if (length <= 0) {
            throw new IllegalArgumentException("URL length must be greater than zero!");
        }

        return ThreadLocalRandom.current()
                .ints('a', 'z')
                .limit(length)
                .map(operand -> ThreadLocalRandom.current().nextBoolean() ? Character.toUpperCase(operand) : operand)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
    }
}
