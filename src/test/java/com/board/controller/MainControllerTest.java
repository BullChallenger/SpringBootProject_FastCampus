package com.board.controller;

import com.board.config.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(SecurityConfig.class)
@WebMvcTest(MainController.class)
public class MainControllerTest {

    private final MockMvc mockMvc;

    public MainControllerTest(@Autowired  MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void rootRedirectArticlesPageTest() throws Exception {
        // given

        // when & then
        mockMvc.perform(get("/"))
                .andExpect(status().is3xxRedirection());
    }
}
