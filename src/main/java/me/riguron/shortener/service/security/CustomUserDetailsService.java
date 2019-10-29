package me.riguron.shortener.service.security;

import me.riguron.shortener.repository.AccountRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

public class CustomUserDetailsService implements UserDetailsService {

    private AccountRepository accountRepository;

    public CustomUserDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String accountId) throws UsernameNotFoundException {
        return accountRepository
                .findOneByAccountIdIgnoreCase(accountId)
                .map(account -> new User(account.getAccountId(), account.getPassword(), Collections.emptyList()))
                .orElseThrow(() -> new UsernameNotFoundException(accountId));
    }
}
