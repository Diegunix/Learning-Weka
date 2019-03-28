package com.learning.learning.controller.dto.pageable;

import java.util.List;

import org.springframework.data.domain.Page;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PageableDTO<T> {

    private List<T> content;
    private int totalPages;
    private int pageNumber;
    private long size;

    @SuppressWarnings("unchecked")
    public PageableDTO(Page<?> page) {
        this.content = (List<T>) page.getContent();
        this.size = page.getTotalElements();
        this.pageNumber = page.getNumber();
        this.totalPages = page.getTotalPages();
    }

    public PageableDTO(Page<?> page, List<T> content) {
        this.content = content;
        this.size = page.getTotalElements();
        this.pageNumber = page.getNumber();
        this.totalPages = page.getTotalPages();
    }
}