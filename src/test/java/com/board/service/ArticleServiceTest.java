package com.board.service;

import com.board.domain.Article;
import com.board.domain.type.SearchType;
import com.board.dto.ArticleDto;
import com.board.dto.ArticleUpdateDto;
import com.board.dto.ArticleWithCommentsDto;
import com.board.repository.ArticleRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@Disabled("구현중")
public class ArticleServiceTest {

    @InjectMocks // Mock 을 주입하는 대상
    private ArticleService sut;

    @Mock // 그 이외의 나머지 Mock
    private ArticleRepository articleRepository;

    @Test
    void searchingArticlesTest() {
        // given

        // when
        Page<ArticleDto> articles = sut.searchArticles(SearchType.TITLE, "search-keywords"); // title, content, id, nickname, hashtag

        // then
        assertThat(articles).isNotNull();
    }

    @Test
    void searchingArticleTest() {
        // given

        // when
        ArticleWithCommentsDto article = sut.getArticle(1L); // title, content, id, nickname, hashtag

        // then
        assertThat(article).isNotNull();
    }

    @Test
    void saveArticleTest() {
        // given
        ArticleDto articleDto = ArticleDto.of("title", "content", "#java", LocalDateTime.now(), "cheshireCat");
        BDDMockito.given(articleRepository.save(any(Article.class))).willReturn(null);

        // when
        sut.saveArticle(articleDto); // title, content, id, nickname, hashtag

        // then
        BDDMockito.then(articleRepository).should().save(any(Article.class));
    }

    @Test
    void updateArticleTest() {
        // given
        BDDMockito.given(articleRepository.save(any(Article.class))).willReturn(null);

        // when
        sut.updateArticle(1L, ArticleUpdateDto.of("title", "content", "#java")); // title, content, id, nickname, hashtag

        // then
        BDDMockito.then(articleRepository).should().save(any(Article.class));
    }

    @Test
    void deleteArticleTest() {
        // given
        BDDMockito.willDoNothing().given(articleRepository).delete(any(Article.class));

        // when
        sut.deleteArticle(1L); // title, content, id, nickname, hashtag

        // then
        BDDMockito.then(articleRepository).should().delete(any(Article.class));
    }
}
