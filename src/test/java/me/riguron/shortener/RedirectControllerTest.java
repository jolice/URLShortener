package me.riguron.shortener;

import me.riguron.shortener.domain.Account;
import me.riguron.shortener.domain.ShortenedUrl;
import me.riguron.shortener.service.URLShorteningService;
import me.riguron.shortener.web.RedirectController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(RedirectController.class)
@ContextConfiguration(classes = WebTestConfiguration.class)
public class RedirectControllerTest {

    private static final String SHORTENED = "aBcDe";

    private static final String REDIRECT_TARGET = "https://google.com";

    private static final int REDIRECT_TYPE = 301;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private URLShorteningService urlShorteningService;

    @Test
    public void whenValidShorteningProvidedThenRedirected() throws Exception {

        ShortenedUrl resultUrl = new ShortenedUrl(SHORTENED, REDIRECT_TARGET, REDIRECT_TYPE, new Account());
        when(urlShorteningService.getOriginUrl(eq(SHORTENED), eq(true))).thenReturn(Optional.of(resultUrl));

        mockMvc.perform(
                get("/" + SHORTENED)
        ).andExpect(
                redirectedUrl(REDIRECT_TARGET)
        ).andExpect(
                status().is(REDIRECT_TYPE)
        ).andExpect(
                header().exists("Location")
        );
    }

    @Test
    public void whenInvalidShorteningProvidedThenSentTo404() throws Exception {

        when(urlShorteningService.getOriginUrl(any(), eq(true))).thenReturn(Optional.empty());

        mockMvc.perform(
                get("/undefined")
        ).andExpect(
                status().isNotFound()
        );
    }


}