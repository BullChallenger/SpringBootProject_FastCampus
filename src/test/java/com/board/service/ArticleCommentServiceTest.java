package com.board.service;

import com.board.domain.Article;
import com.board.domain.ArticleComment;
import com.board.domain.type.SearchType;
import com.board.dto.ArticleCommentDto;
import com.board.dto.ArticleDto;
import com.board.repository.ArticleCommentRepository;
import com.board.repository.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class ArticleCommentServiceTest {

    @InjectMocks // Mock 을 주입하는 대상
    private ArticleCommentService sut;

    @Mock // 그 이외의 나머지 Mock
    private ArticleCommentRepository articleCommentRepository;

    @Mock
    private ArticleRepository articleRepository;

    @Test
    void searchingArticleCommentsTest() {
        // given
        Long articleId = 1L;
        BDDMockito.given(articleRepository.findById(articleId))
                .willReturn(Optional.of(Article.of("title", "content", "#java")));

        // when
        List<ArticleCommentDto> articleComments = sut.searchArticleComments(articleId); // title, content, id, nickname, hashtag

        // then
        assertThat(articleComments).isNotNull();
        BDDMockito.then(articleRepository).should().findById(articleId);
    }

    @Test
    void saveArticleCommentTest() {
        // given
        ArticleDto articleDto = ArticleDto.of("title", "content", "#java", LocalDateTime.now(), "cheshireCat");
        BDDMockito.given(articleRepository.save(any(Article.class))).willReturn(null);

        // when
        sut.saveArticle(articleDto); // title, content, id, nickname, hashtag

        // then
        BDDMockito.then(articleRepository).should().save(any(Article.class));
    }

}
