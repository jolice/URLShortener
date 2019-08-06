package me.riguron.shortener.repository;


import me.riguron.shortener.domain.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, String> {

    boolean existsByAccountIdIgnoreCase(String name);

    Optional<Account> findOneByAccountIdIgnoreCase(String name);
}
