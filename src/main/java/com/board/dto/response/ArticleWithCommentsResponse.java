package com.board.dto.response;

import com.board.dto.ArticleWithCommentsDto;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
public class ArticleWithCommentsResponse {

    private final Long id;

    private final String title;

    private final String content;

    private final String hashtag;

    private final LocalDateTime createdAt;

    private final String email;

    private final String nickname;

    private final String userId;

    private final Set<ArticleCommentResponse> articleCommentResponse;

    public ArticleWithCommentsResponse(Long id, String title, String content, String hashtag, LocalDateTime createdAt,
                                       String email, String nickname, String userId, Set<ArticleCommentResponse> articleCommentResponse) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
        this.createdAt = createdAt;
        this.email = email;
        this.nickname = nickname;
        this.userId = userId;
        this.articleCommentResponse = articleCommentResponse;
    }

    public static ArticleWithCommentsResponse of(Long id, String title, String content, String hashtag, LocalDateTime createdAt,
                                                 String email, String nickname, String userId, Set<ArticleCommentResponse> articleCommentResponses) {
        return new ArticleWithCommentsResponse(id, title, content, hashtag, createdAt, email, nickname, userId, articleCommentResponses);
    }

    public static ArticleWithCommentsResponse from(ArticleWithCommentsDto articleWithCommentsDto) {
        String nickname = articleWithCommentsDto.getUserAccountDto().getNickname();
        if (nickname == null || nickname.isBlank()) {
            nickname = articleWithCommentsDto.getUserAccountDto().getUserId();
        }

        return new ArticleWithCommentsResponse(
                articleWithCommentsDto.getId(),
                articleWithCommentsDto.getTitle(),
                articleWithCommentsDto.getContent(),
                articleWithCommentsDto.getHashtag(),
                articleWithCommentsDto.getCreatedAt(),
                articleWithCommentsDto.getUserAccountDto().getEmail(),
                nickname,
                articleWithCommentsDto.getUserAccountDto().getUserId(),
                organizeChildComments(articleWithCommentsDto.getArticleCommentDtos())
        );
    }
}
