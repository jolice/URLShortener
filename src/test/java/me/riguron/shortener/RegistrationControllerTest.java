package me.riguron.shortener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.riguron.shortener.domain.Account;
import me.riguron.shortener.entity.register.URLRegistration;
import me.riguron.shortener.service.AccountService;
import me.riguron.shortener.service.URLShorteningService;
import me.riguron.shortener.web.RegistrationController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(RegistrationController.class)
@ContextConfiguration(classes = WebTestConfiguration.class)
public class RegistrationControllerTest {

    private static final String URL = "/api/register";

    private static final int REDIRECT_TYPE = 302;

    private static final String SHORTENING_TARGET = "to_shorten";

    private static final String SHORTENING = "aBcDe";


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @MockBean
    private URLShorteningService urlShorteningService;

    @Test
    @WithMockUser(username = "username", password = "pass")
    public void whenRegisteredWithValidAccountThenRegistered() throws Exception {

        Account account = mock(Account.class);
        when(accountService.findById("username")).thenReturn(Optional.of(account));

        when(urlShorteningService.shorten(eq(account), eq(SHORTENING_TARGET), eq(REDIRECT_TYPE))).
                thenReturn(SHORTENING);

        URLRegistration urlRegistration = new URLRegistration(SHORTENING_TARGET, REDIRECT_TYPE);


        this.mockMvc.perform(
                post(URL).contentType(MediaType.APPLICATION_JSON).content(registrationToString(urlRegistration))
        ).andExpect(
                status().isOk()
        ).andExpect(
                jsonPath("$.url").value(SHORTENING)
        );
    }

    @Test
    @WithMockUser(username = "username", password = "pass")
    public void whenAccountDoesNotExistsThenExceptionFired() throws Exception {
        when(accountService.findById("username")).thenReturn(Optional.empty());
        URLRegistration urlRegistration = new URLRegistration(SHORTENING_TARGET, REDIRECT_TYPE);
        this.mockMvc.perform(
                post(URL).contentType(MediaType.APPLICATION_JSON).content(registrationToString(urlRegistration))
        ).andExpect(
                status().isConflict()
        ).andExpect(
                content().string(containsString("issue"))
        );
    }

    @Test
    public void whenNotAuthorizedThenOperationDeclined() throws Exception {

        URLRegistration urlRegistration = new URLRegistration(SHORTENING_TARGET, REDIRECT_TYPE);

        this.mockMvc.perform(
                post(URL).contentType(MediaType.APPLICATION_JSON).content(registrationToString(urlRegistration))
        ).andExpect(
                status().isUnauthorized()
        );
    }

    private String registrationToString(URLRegistration urlRegistration) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(urlRegistration);
    }
}