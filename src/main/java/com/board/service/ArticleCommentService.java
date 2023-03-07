package com.board.service;

import com.board.dto.ArticleCommentDto;
import com.board.repository.ArticleCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticleCommentService {

    private final ArticleCommentRepository articleCommentRepository;

    public List<ArticleCommentDto> searchArticleComments(Long articleId) {
        return List.of();
    }
}
