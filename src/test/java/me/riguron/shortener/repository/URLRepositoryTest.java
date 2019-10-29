package me.riguron.shortener.repository;

import me.riguron.shortener.domain.Account;
import me.riguron.shortener.domain.ShortenedUrl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.LongStream.range;
import static org.junit.Assert.*;

@DataJpaTest
@RunWith(SpringRunner.class)
public class URLRepositoryTest {

    @Autowired
    private URLRepository urlRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Test
    public void testQueryAccounts() {


        Account account = new Account("login", "password");
        accountRepository.save(account);

        Account second = new Account("second", "other");
        accountRepository.save(second);

        ShortenedUrl one = new ShortenedUrl("aBc", "google.com", 302, account);
        ShortenedUrl two = new ShortenedUrl("eDf", "apple.com", 301, account);
        ShortenedUrl other = new ShortenedUrl("of", "amazon.com", 301, second);

        range(0, 3).forEach(x -> one.use());
        range(0, 5).forEach(x -> two.use());
        range(0, 5).forEach(x -> other.use());

        urlRepository.save(one);
        urlRepository.save(two);
        urlRepository.save(other);

        Map<String, List<ShortenedUrl>> login = urlRepository.getShorteningsFor("login").stream()
                .collect(Collectors.groupingBy(ShortenedUrl::getShortening));

        assertEquals(2, login.size());

        List<ShortenedUrl> firstList = login.get("aBc");
        assertEquals(1, firstList.size());
        assertEquals(3, firstList.get(0).getUses());

        List<ShortenedUrl> secondList = login.get("eDf");
        assertEquals(1, secondList.size());
        assertEquals(5, secondList.get(0).getUses());

    }
}