package me.riguron.shortener.generator;

import me.riguron.shortener.service.generator.PasswordGenerator;
import org.junit.Test;

import static org.junit.Assert.*;

public class PasswordGeneratorTest {

    @Test
    public void whenGeneratePasswordOfFixedLengthThenReturnedLength() {
        PasswordGenerator passwordGenerator = new PasswordGenerator();
        String password = passwordGenerator.generatePassword(8);
        assertNotNull(password);
        assertEquals(password.length(), 8);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPassNegativeArgumentThenExceptionRaised() {
        new PasswordGenerator().generatePassword(-5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPassZeroArgumentThenExceptionRaised() {
        new PasswordGenerator().generatePassword(0);
    }
}