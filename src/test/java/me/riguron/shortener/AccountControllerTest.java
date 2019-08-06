package me.riguron.shortener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.riguron.shortener.entity.account.AccountId;
import me.riguron.shortener.service.AccountService;
import me.riguron.shortener.web.AccountController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountController.class)
@ContextConfiguration(classes = WebTestConfiguration.class)
public class AccountControllerTest {

    private static final String URL = "/api/account";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @Test
    public void whenCreateAccountWithInexistentIdThenCreated() throws Exception {


        when(accountService.createAccount(eq("INEXISTENT"))).thenReturn(Optional.of("password"));

        this.mockMvc.perform(
                post(URL).contentType(MediaType.APPLICATION_JSON).content(accountIdToJson("INEXISTENT"))
        ).andExpect(
                status().isOk()
        ).andExpect(
                jsonPath("$.success").value(true)
        ).andExpect(
                jsonPath("$.password").value("password")
        );
    }

    @Test
    public void whenCreateAccountWithExistingIdThenRejected() throws Exception {
        when(accountService.createAccount(eq("EXISTING"))).thenReturn(Optional.empty());

        this.mockMvc.perform(
                post(URL).contentType(MediaType.APPLICATION_JSON).content(accountIdToJson("EXISTING"))
        ).andExpect(
                status().isOk()
        ).andExpect(
                jsonPath("$.success").value(false)
        );
    }

    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        AccountId accountId = new AccountId("222");
        System.out.println(objectMapper.writeValueAsString(accountId));
    }

    private String accountIdToJson(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        AccountId accountId = new AccountId(json);
        return objectMapper.writeValueAsString(accountId);
    }


}
