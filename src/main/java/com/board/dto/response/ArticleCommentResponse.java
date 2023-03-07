package com.board.dto.response;

import com.board.dto.ArticleCommentDto;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ArticleCommentResponse {

    private final Long id;

    private final String content;

    private final LocalDateTime createdAt;

    private final String email;

    private final String nickname;

    private final String userId;

    public ArticleCommentResponse(Long id, String content, LocalDateTime createdAt, String email, String nickname, String userId) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.email = email;
        this.nickname = nickname;
        this.userId = userId;
    }

    public static ArticleCommentResponse of(Long id, String content, LocalDateTime createdAt, String email, String nickname, String userId) {
        return ArticleCommentResponse.of(id, content, createdAt, email, nickname, userId);
    }

    public static ArticleCommentResponse from(ArticleCommentDto articleCommentDto) {
        String nickname = articleCommentDto.getUserAccountDto().getNickname();
        if (nickname == null || nickname.isBlank()) {
            nickname = articleCommentDto.getUserAccountDto().getUserId();
        }

        return ArticleCommentResponse.of(
                articleCommentDto.getId(),
                articleCommentDto.getContent(),
                articleCommentDto.getCreatedAt(),
                articleCommentDto.getUserAccountDto().getEmail(),
                nickname,
                articleCommentDto.getUserAccountDto().getUserId()
        );
    }

}