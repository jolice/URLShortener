package me.riguron.shortener;

import me.riguron.shortener.domain.Account;
import me.riguron.shortener.domain.ShortenedUrl;
import me.riguron.shortener.service.AccountService;
import me.riguron.shortener.service.URLShorteningService;
import me.riguron.shortener.web.StatisticController;
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

import java.util.Collections;
import java.util.HashSet;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(StatisticController.class)
@ContextConfiguration(classes = WebTestConfiguration.class)
public class StatisticControllerTest {

    private static final String URL = "/api/statistic/";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private URLShorteningService urlShorteningService;

    @Test
    @WithMockUser(username = "username", password = "pass")
    public void statistics() throws Exception {

        when(urlShorteningService.getShorteningsFor("username")).thenReturn(
                new HashSet<>(
                        Collections.singletonList(
                                new ShortenedUrl("short", "url1", 301, new Account())
                        )
                )
        );

        this.mockMvc.perform(
                get(URL).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                status().isOk()
        ).andExpect(
                jsonPath("$.url1").value(0)
        );
    }

    @Test
    public void statisticsAuthorized() throws Exception {
        this.mockMvc.perform(
                get(URL).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                status().isUnauthorized()
        );
    }

}