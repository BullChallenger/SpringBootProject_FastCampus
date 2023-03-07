package com.board.dto;

import com.board.domain.ArticleComment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ArticleCommentDto {

    private final Long id;

    private final Long articleId;

    private final UserAccountDto userAccountDto;

    private final String content;

    private final LocalDateTime createdAt;

    private final String createdBy;

    private final LocalDateTime modifiedAt;

    private final String modifiedBy;

    public ArticleCommentDto(Long id, Long articleId, UserAccountDto userAccountDto, String content,
                             LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        this.id = id;
        this.articleId = articleId;
        this.userAccountDto = userAccountDto;
        this.content = content;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.modifiedAt = modifiedAt;
        this.modifiedBy = modifiedBy;
    }

    public static ArticleCommentDto of(Long id, Long articleId, UserAccountDto userAccountDto, String content,
                                       LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new ArticleCommentDto(id, articleId, userAccountDto, content, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static ArticleCommentDto from(ArticleComment articleComment) {
        return new ArticleCommentDto(
                articleComment.getId(),
                articleComment.getArticle().getId(),
                UserAccountDto.from(articleComment.getUserAccount()),
                articleComment.getContent(),
                articleComment.getCreatedAt(),
                articleComment.getCreatedBy(),
                articleComment.getModifiedAt(),
                articleComment.getModifiedBy()
        );
    }
}
