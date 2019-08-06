package me.riguron.shortener.security;

import me.riguron.shortener.domain.Account;
import me.riguron.shortener.repository.AccountRepository;
import me.riguron.shortener.service.security.CustomUserDetailsService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CustomUserDetailsServiceTest {

    private AccountRepository accountRepository;
    private CustomUserDetailsService customUserDetailsService;

    @Before
    public void setUp() {
        this.accountRepository = mock(AccountRepository.class);
        customUserDetailsService = new CustomUserDetailsService(accountRepository);
    }

    @Test
    public void whenLoadExistingAccountByUsernameThenLoaded() {
        final String accountId = "id";
        final String password = "password";

        Account account = mock(Account.class);
        when(account.getPassword()).thenReturn(password);
        when(accountRepository.findOneByAccountIdIgnoreCase(accountId)).thenReturn(Optional.of(account));

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(accountId);
        assertNotNull(userDetails);
        assertEquals(userDetails.getUsername(), accountId);
        assertEquals(userDetails.getPassword(), password);
    }


    @Test(expected = UsernameNotFoundException.class)
    public void whenLoadInexistentAccountByUsernameThenNotLoaded() {
       customUserDetailsService.loadUserByUsername("username");
    }

}