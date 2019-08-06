package me.riguron.shortener.web;

import me.riguron.shortener.entity.account.AccountId;
import me.riguron.shortener.entity.account.AccountPasswordResponse;
import me.riguron.shortener.entity.account.AccountResponse;
import me.riguron.shortener.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping("/api")
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping(value = "/account", consumes = "application/json", produces = "application/json")
    public AccountResponse create(@RequestBody AccountId accountId) {
        Optional<String> accountCreationResult = accountService.createAccount(accountId.getId());
        if (accountCreationResult.isPresent()) {
            String password = accountCreationResult.get();
            return new AccountPasswordResponse(true, "Your account is opened", password);
        } else {
            return new AccountResponse(false, "Account with specified ID already exists!");
        }
    }
}
