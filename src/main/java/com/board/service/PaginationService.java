package com.board.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaginationService {

    private static final int BAR_LENGTH = 5;

    public List<Integer> getPaginationBarNumber(int currentPageNumber, int totalPages) {
        return null;
    }

    public int currentBarLength() {
        return BAR_LENGTH;
    }
}
