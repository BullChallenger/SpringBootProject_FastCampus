package com.board.dto.response;

import com.board.dto.ArticleCommentDto;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

@Getter
public class ArticleCommentResponse {

    private final Long id;

    private final String content;

    private final LocalDateTime createdAt;

    private final String email;

    private final String nickname;

    private final String userId;

    private final Long parentCommentId;

    private final Set<ArticleCommentResponse> childComments;

    public ArticleCommentResponse(Long id, String content, LocalDateTime createdAt, String email, String nickname, String userId,
                                  Long parentCommentId, Set<ArticleCommentResponse> childComments) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.email = email;
        this.nickname = nickname;
        this.userId = userId;
        this.parentCommentId = parentCommentId;
        this.childComments = childComments;
    }

    public static ArticleCommentResponse of(Long id, String content, LocalDateTime createdAt, String email, String nickname, String userId) {
        return ArticleCommentResponse.of(id, content, createdAt, email, nickname, userId, null);
    }

    public static ArticleCommentResponse of(Long id, String content, LocalDateTime createdAt, String email, String nickname, String userId, Long parentCommentId) {
        Comparator<ArticleCommentResponse> childCommentComparator = Comparator
                .comparing(ArticleCommentResponse::getCreatedAt)
                .thenComparing(ArticleCommentResponse::getId);

        return new ArticleCommentResponse(id, content, createdAt, email, nickname, userId, parentCommentId, new TreeSet<>(childCommentComparator));
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
                articleCommentDto.getUserAccountDto().getUserId(),
                articleCommentDto.getParentCommentId()
        );
    }

    public boolean hasParentComment() {
        return parentCommentId != null;
    }

}