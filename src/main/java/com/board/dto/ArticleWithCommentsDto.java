package com.board.dto;

import com.board.domain.Article;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class ArticleWithCommentsDto {

    private final Long id;

    private final UserAccountDto userAccountDto;

    private final Set<ArticleCommentDto> articleCommentDtos;

    private final String title;

    private final String content;

    private final String hashtag;

    private final LocalDateTime createdAt;

    private final String createdBy;

    private final LocalDateTime modifiedAt;

    private final String modifiedBy;

    public ArticleWithCommentsDto(Long id, UserAccountDto userAccountDto, Set<ArticleCommentDto> articleCommentDtos,
                                  String title, String content, String hashtag, LocalDateTime createdAt, String createdBy,
                                  LocalDateTime modifiedAt, String modifiedBy) {
        this.id = id;
        this.userAccountDto = userAccountDto;
        this.articleCommentDtos = articleCommentDtos;
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.modifiedAt = modifiedAt;
        this.modifiedBy = modifiedBy;
    }

    public static ArticleWithCommentsDto of(Long id, UserAccountDto userAccountDto, Set<ArticleCommentDto> articleCommentDtos,
                                            String title, String content, String hashtag, LocalDateTime createdAt, String createdBy,
                                            LocalDateTime modifiedAt, String modifiedBy) {
        return new ArticleWithCommentsDto(id, userAccountDto, articleCommentDtos, title, content, hashtag, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static ArticleWithCommentsDto from(Article entity) {
        return new ArticleWithCommentsDto(
                entity.getId(),
                UserAccountDto.from(entity.getUserAccount()),
                entity.getArticleComments().stream()
                        .map(ArticleCommentDto::from)
                        .collect(Collectors.toCollection(LinkedHashSet::new))
                ,
                entity.getTitle(),
                entity.getContent(),
                entity.getHashtag(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

}
