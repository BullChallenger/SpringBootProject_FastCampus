package com.board.dto.response;

import com.board.dto.ArticleDto;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
public class ArticleResponse {

    private final Long id;

    private final String title;

    private final String content;

    private final Set<String> hashtags;

    private final LocalDateTime createdAt;

    private final String email;

    private final String nickname;

    public ArticleResponse(Long id, String title, String content, Set<String> hashtags, LocalDateTime createdAt, String email,
                           String nickname) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.hashtags = hashtags;
        this.createdAt = createdAt;
        this.email = email;
        this.nickname = nickname;
    }

    public static ArticleResponse of(Long id, String title, String content, Set<String> hashtags, LocalDateTime createdAt, String email, String nickname) {
        return new ArticleResponse(id, title, content, hashtags, createdAt, email, nickname);
    }

    public static ArticleResponse from(ArticleDto articleDto) {
        String nickname = articleDto.getUserAccountDto().getNickname();
        if (nickname == null || nickname.isBlank()) {
            nickname = articleDto.getUserAccountDto().getUserId();
        }

        return new ArticleResponse(
                articleDto.getId(),
                articleDto.getTitle(),
                articleDto.getContent(),
                articleDto.,
                articleDto.getCreatedAt(),
                articleDto.getUserAccountDto().getEmail(),
                nickname
        );
    }
}
