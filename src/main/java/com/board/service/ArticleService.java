package com.board.service;

import com.board.domain.Article;
import com.board.domain.Hashtag;
import com.board.domain.UserAccount;
import com.board.domain.type.SearchType;
import com.board.dto.ArticleDto;
import com.board.dto.ArticleUpdateDto;
import com.board.dto.ArticleWithCommentsDto;
import com.board.repository.ArticleRepository;
import com.board.repository.HashtagRepository;
import com.board.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ArticleService {
    private final UserAccountRepository userAccountRepository;
    private final ArticleRepository articleRepository;
    private final HashtagService hashtagService;
    private final HashtagRepository hashtagRepository;

    @Transactional(readOnly = true)
    public Page<ArticleDto> searchArticles(SearchType searchType, String searchKeyword, Pageable pageable) {
        if (searchKeyword == null || searchKeyword.isBlank()) {
            return articleRepository.findAll(pageable).map(ArticleDto::from);
        }

        return switch (searchType) {
            case TITLE -> articleRepository.findByTitleContaining(searchKeyword, pageable).map(ArticleDto::from);
            case CONTENT -> articleRepository.findByContentContaining(searchKeyword, pageable).map(ArticleDto::from);
            case ID -> articleRepository.findByUserAccount_UserIdContaining(searchKeyword, pageable).map(ArticleDto::from);
            case NICKNAME -> articleRepository.findByUserAccount_NicknameContaining(searchKeyword, pageable).map(ArticleDto::from);
            case HASHTAG -> articleRepository.findByHashtag("#" + searchKeyword, pageable).map(ArticleDto::from);
        };
    }

    @Transactional(readOnly = true)
    public ArticleWithCommentsDto getArticleWithComments(Long articleId) {
        return articleRepository.findById(articleId)
                .map(ArticleWithCommentsDto::from)
                .orElseThrow(() -> new EntityNotFoundException("게시글이 없습니다 - articleId: " + articleId));
    }

    @Transactional(readOnly = true)
    public ArticleDto getArticle(long articleId) {
        return articleRepository.findById(articleId).map(ArticleDto::from)
                .orElseThrow(() -> new EntityNotFoundException("해당 게시글이 존재하지 않습니다."));
    }

    public void saveArticle(ArticleDto articleDto) {
        UserAccount userAccount = userAccountRepository.getReferenceById(articleDto.getUserAccountDto().getUserId());
        Set<Hashtag> hashtags = renewHashtagsFromContent(articleDto.getContent());

        Article article = articleDto.toEntity(userAccount);
        article.addHashtags(hashtags);
        articleRepository.save(article);
    }

    public void updateArticle(Long articleId, ArticleDto articleDto) {
        try {
            Article article = articleRepository.getReferenceById(articleDto.getId());
            UserAccount userAccount = userAccountRepository.getReferenceById(articleDto.getUserAccountDto().getUserId());

            if (article.getUserAccount().equals(userAccount)) {
                if (articleDto.getTitle() != null) {
                    article.setTitle(articleDto.getTitle());
                }
                if (articleDto.getContent() != null) {
                    article.setTitle(articleDto.getContent());
                }
                article.setHashtag(articleDto.getHashtag());
            }
        } catch (EntityNotFoundException e) {
            log.warn("게시글 업데이트 실패, 게시글을 찾을 수 없습니다.");
        }
    }

    public void deleteArticle(long articleId, String userId) {
        articleRepository.deleteByIdAndUserAccount_UserId(articleId, userId);
    }

    public long getArticleCount() {
        return articleRepository.count();
    }

    @Transactional(readOnly = true)
    public Page<ArticleDto> searchArticlesViaHashtag(String hashtag, Pageable pageable) {
        if (hashtag == null || hashtag.isBlank()) {
            return Page.empty(pageable);
        }

        return articleRepository.findByHashtag(hashtag, pageable).map(ArticleDto::from);
    }

    public List<String> getHashtags() {
        return articleRepository.findAllDistinctHashtags();
    }

    private Set<Hashtag> renewHashtagsFromContent(String content) {
    }
}
