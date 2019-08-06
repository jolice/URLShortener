package me.riguron.shortener;

import me.riguron.shortener.domain.Account;
import me.riguron.shortener.domain.ShortenedUrl;
import me.riguron.shortener.repository.AccountRepository;
import me.riguron.shortener.service.AccountService;
import me.riguron.shortener.service.generator.PasswordGenerator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class AccountServiceTest {

    private static final String NAME = "name";

    private AccountRepository accountRepository;
    private AccountService accountService;

    @Before
    public void setUp() {
        this.accountRepository = mock(AccountRepository.class);
        this.accountService = new AccountService(new PasswordGenerator(), accountRepository, new BCryptPasswordEncoder());
        this.accountService.setPasswordLength(10);
    }

    @Test
    public void whenFindByIdThenReturnsIdFromRepository() {
        Account account = mock(Account.class);
        Optional<Account> repositoryQueryResult = Optional.of(account);
        when(accountRepository.findById(NAME)).thenReturn(repositoryQueryResult);
        Optional<Account> serviceQueryResult = accountService.findById(NAME);
        assertEquals(repositoryQueryResult, serviceQueryResult);
    }

    @Test
    public void whenCreateAccountWithExistingNameThenReturnsFalse() {
        when(accountRepository.existsByAccountIdIgnoreCase(NAME)).thenReturn(true);
        assertEquals(accountService.createAccount(NAME), Optional.empty());
    }

    @Test
    public void whenCreateAccountWithInexistentIdThenCreated() {
        when(accountRepository.existsByAccountIdIgnoreCase(NAME)).thenReturn(false);
        accountService.createAccount(NAME);
        verify(accountRepository, times(1)).save(any());
    }

    @Test
    public void getShortenedUrls() {
        Account account = new Account();
        ShortenedUrl accountsShortenedUrl = new ShortenedUrl();
        account.getShortenedUrls().add(accountsShortenedUrl);
        Optional<Account> repositoryOptionalResult = Optional.of(account);
        when(accountRepository.findOneByAccountIdIgnoreCase(NAME)).thenReturn(repositoryOptionalResult);
        Set<ShortenedUrl> shortenedUrls = accountService.getShortenedUrls(NAME);
        assertEquals(shortenedUrls, account.getShortenedUrls());
    }
}