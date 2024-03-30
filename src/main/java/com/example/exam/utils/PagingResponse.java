package com.example.exam.utils;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PagingResponse<T> {
    private List<T> data;
    private int currentPage;
    private Long totalItems;
    private int totalPages;
}
