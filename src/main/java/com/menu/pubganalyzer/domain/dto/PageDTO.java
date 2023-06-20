package com.menu.pubganalyzer.domain.dto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

public class PageDTO<T> extends PageImpl<T> {
    private static final int DEFAULT_DISPLAY_PAGES_NUM = 5;
    private final int displayPagesNum;
    private final int paginationStartNum;
    private final int paginationEndNum;

    private PageDTO(Page<T> page, int displayPagesNum) {
        super(page.getContent(), page.getPageable(), page.getTotalElements());
        this.displayPagesNum = displayPagesNum;
        int currentPageNum = page.getPageable().getPageNumber();
        this.paginationStartNum = (currentPageNum / displayPagesNum) * displayPagesNum + 1;
        this.paginationEndNum = Math.min(paginationStartNum + displayPagesNum - 1, page.getTotalPages()); // 끝 페이지 번호
    }

    public static <T> PageDTO<T> of(Page<T> page) {
        return new PageDTO<>(page, DEFAULT_DISPLAY_PAGES_NUM);
    }

    public static <T> PageDTO<T> of(Page<T> page, int display) {
        return new PageDTO<>(page, display);
    }

    public static <T> PageDTO<T> empty(Class<T> clazz) {
        return PageDTO.of(Page.empty());
    }

    public int getPreviousPageNum() {
        return this.previousOrFirstPageable().getPageNumber();
    }

    public int getNextPageNum() {
        return this.nextOrLastPageable().getPageNumber();
    }

    public int getPaginationStartNum() {
        return paginationStartNum;
    }

    public int getPaginationEndNum() {
        return paginationEndNum;
    }

    public int getDisplayPagesNum() {
        return displayPagesNum;
    }
}
