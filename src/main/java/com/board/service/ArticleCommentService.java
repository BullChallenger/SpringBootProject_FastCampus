package com.board.service;

import com.board.domain.Article;
import com.board.domain.ArticleComment;
import com.board.domain.UserAccount;
import com.board.dto.ArticleCommentDto;
import com.board.repository.ArticleCommentRepository;
import com.board.repository.ArticleRepository;
import com.board.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticleCommentService {

    private final ArticleCommentRepository articleCommentRepository;
    private final ArticleRepository articleRepository;
    private final UserAccountRepository userAccountRepository;

    @Transactional(readOnly = true)
    public List<ArticleCommentDto> searchArticleComments(Long articleId) {
        return articleCommentRepository.findByArticle_Id(articleId)
                        .stream()
                        .map(ArticleCommentDto::from)
                        .toList();
    }

    public void saveArticleComment(ArticleCommentDto articleCommentDto) {
        try {
            Article article = articleRepository.getReferenceById(articleCommentDto.getArticleId());
            UserAccount userAccount = userAccountRepository.getReferenceById(articleCommentDto.getUserAccountDto().getUserId());
            ArticleComment articleComment = articleCommentDto.toEntity(article, userAccount);
        }
    }
}
