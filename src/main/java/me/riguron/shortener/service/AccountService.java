package me.riguron.shortener.service;

import me.riguron.shortener.domain.Account;
import me.riguron.shortener.domain.ShortenedUrl;
import me.riguron.shortener.repository.AccountRepository;
import me.riguron.shortener.service.generator.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@Service
public class AccountService {


    private int passwordLength;

    private PasswordGenerator passwordGenerator;

    private AccountRepository repository;

    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AccountService(PasswordGenerator passwordGenerator, AccountRepository repository, PasswordEncoder bCryptPasswordEncoder) {
        this.passwordGenerator = passwordGenerator;
        this.repository = repository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Transactional
    public Optional<Account> findById(String accountId) {
        return repository.findById(accountId);
    }



    @Transactional
    public Optional<String> createAccount(String accountID) {
        if (repository.existsByAccountIdIgnoreCase(accountID)) {
            return Optional.empty();
        } else {
            String password = passwordGenerator.generatePassword(passwordLength);
            String encodedPassword = bCryptPasswordEncoder.encode(password);
            Account persistentAccount = new Account(accountID, encodedPassword);
            repository.save(persistentAccount);
            return Optional.ofNullable(password);
        }
    }



    @Transactional
    public Set<ShortenedUrl> getShortenedUrls(String accountId) {
        return repository.findOneByAccountIdIgnoreCase(accountId).map(Account::getShortenedUrls).orElse(Collections.emptySet());
    }

    @Value("${password.length}")
    public void setPasswordLength(int passwordLength) {
        this.passwordLength = passwordLength;
    }
}
