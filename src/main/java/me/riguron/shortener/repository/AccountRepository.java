package me.riguron.shortener.repository;

import me.riguron.shortener.domain.Account;
import me.riguron.shortener.domain.ShortenedUrl;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;

public interface AccountRepository extends CrudRepository<Account, String> {

    boolean existsByAccountIdIgnoreCase(String name);

    Optional<Account> findOneByAccountIdIgnoreCase(String name);

    Optional<Account> getOneByAccountIdIgnoreCase(String name);
}
