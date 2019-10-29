package me.riguron.shortener.web;

import me.riguron.shortener.entity.register.RegistrationResponse;
import me.riguron.shortener.entity.register.URLRegistration;
import me.riguron.shortener.service.AccountService;
import me.riguron.shortener.service.URLShorteningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;


@RestController
@RequestMapping("/api")
public class RegistrationController {

    private URLShorteningService urlShorteningService;

    private AccountService accountService;

    @Autowired
    public RegistrationController(URLShorteningService urlShorteningService, AccountService accountService) {
        this.urlShorteningService = urlShorteningService;
        this.accountService = accountService;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
    public RegistrationResponse doRegister(@RequestBody @Valid URLRegistration registration, Principal principal) {
        return new RegistrationResponse(
                urlShorteningService.shorten(accountService.getOneById(principal.getName()).orElseThrow(() -> new IllegalStateException("Security issue")
                ), registration.getUrl(), registration.getRedirectType()));

    }
}

