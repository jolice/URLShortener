package me.riguron.shortener.service.generator;

import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;


@Component
public class PasswordGenerator {


    public String generatePassword(int length) {

        if (length <= 0) {
            throw new IllegalArgumentException("Length must be positive");
        }

        StringBuilder password = new StringBuilder();
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < length; i++) {
            if (random.nextBoolean()) {
                int randomInt = random.nextInt(10);
                password.append(randomInt);
            } else {
                char randomChar = (char) random.nextInt('a', 'z' + 1);
                if (random.nextBoolean()) {
                    randomChar = Character.toUpperCase(randomChar);
                }
                password.append(randomChar);
            }
        }
        return password.toString();
    }

}
