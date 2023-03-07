package com.board.dto.response;

import com.board.dto.ArticleDto;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ArticleResponse {

    private final Long id;

    private final String title;

    private final String content;

    private final String hashtag;

    private final LocalDateTime createdAt;

    private final String email;

    private final String nickname;

    public ArticleResponse(Long id, String title, String content, String hashtag, LocalDateTime createdAt, String email,
                           String nickname) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
        this.createdAt = createdAt;
        this.email = email;
        this.nickname = nickname;
    }

    public static ArticleResponse of(Long id, String title, String content, String hashtag, LocalDateTime createdAt, String email, String nickname) {
        return new ArticleResponse(id, title, content, hashtag, createdAt, email, nickname);
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
                articleDto.getHashtag(),
                articleDto.getCreatedAt(),
                articleDto.getUserAccountDto().getEmail(),
                nickname
        );
    }
}
