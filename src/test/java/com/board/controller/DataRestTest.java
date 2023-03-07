package com.board.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Data Rest Test - API")
@AutoConfigureMockMvc
@SpringBootTest
@Transactional
@Disabled("개발중")
public class DataRestTest {

    private final MockMvc mockMvc;

    public DataRestTest(
            @Autowired MockMvc mockMvc
    ) {
        this.mockMvc = mockMvc;
    }

    @Test
    @DisplayName("[API]_게시글_리스트_조회")
    void articleListTest() throws Exception {
        // given

        // when & then
        mockMvc.perform(get("/api/articles"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
    }

    @Test
    @DisplayName("[API]_게시글_단일_조회")
    void articleTest() throws Exception {
        // given

        // when & then
        mockMvc.perform(get("/api/articles/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
    }

    @Test
    @DisplayName("[API]_게시글_댓글_리스트_조회")
    void articleCommentListInArticleTest() throws Exception {
        // given

        // when & then
        mockMvc.perform(get("/api/articles/articleComments"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
    }

    @Test
    @DisplayName("[API]_댓글_리스트_조회")
    void articleCommentListTest() throws Exception {
        // given

        // when & then
        mockMvc.perform(get("/api/articleComments"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
    }

    @Test
    @DisplayName("[API]_댓글_단일_조회")
    void articleCommentTest() throws Exception {
        // given

        // when & then
        mockMvc.perform(get("/api/articleComments/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
    }
}
