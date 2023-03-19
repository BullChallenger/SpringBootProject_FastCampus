package com.board.dto;

import com.board.domain.Article;
import com.board.domain.ArticleComment;
import com.board.domain.UserAccount;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ArticleCommentDto {

    private final Long id;

    private final Long articleId;

    private final UserAccountDto userAccountDto;

    private final Long parentCommentId;

    private final String content;

    private final LocalDateTime createdAt;

    private final String createdBy;

    private final LocalDateTime modifiedAt;

    private final String modifiedBy;

    public ArticleCommentDto(Long id, Long articleId, UserAccountDto userAccountDto, Long parentCommentId, String content,
                             LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        this.id = id;
        this.articleId = articleId;
        this.userAccountDto = userAccountDto;
        this.parentCommentId = parentCommentId;
        this.content = content;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.modifiedAt = modifiedAt;
        this.modifiedBy = modifiedBy;
    }

    public static ArticleCommentDto of(Long articleId, UserAccountDto userAccountDto, String content) {
        return ArticleCommentDto.of(articleId, userAccountDto, null, content);
    }

    public static ArticleCommentDto of(Long articleId, UserAccountDto userAccountDto, Long parentCommentId, String content) {
        return ArticleCommentDto.of(null, articleId, userAccountDto, parentCommentId, content, null, null, null, null);
    }

    public static ArticleCommentDto of(Long id, Long articleId, UserAccountDto userAccountDto, Long parentCommentId, String content,
                                       LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return ArticleCommentDto.of(id, articleId, userAccountDto, parentCommentId, content, null, null, null, null);
    }

    public static ArticleCommentDto from(ArticleComment articleComment) {
        return new ArticleCommentDto(
                articleComment.getId(),
                articleComment.getArticle().getId(),
                UserAccountDto.from(articleComment.getUserAccount()),
                articleComment.getParentCommentId(),
                articleComment.getContent(),
                articleComment.getCreatedAt(),
                articleComment.getCreatedBy(),
                articleComment.getModifiedAt(),
                articleComment.getModifiedBy()
        );
    }

    public ArticleComment toEntity(Article article, UserAccount userAccount) {
        return ArticleComment.of(
                article,
                userAccount,
                content
        );
    }

}
