package com.menu.pubganalyzer.common.dto.response;

import org.springframework.data.domain.Page;

import java.util.List;

public class PageDTO<T> {
    private static final int DEFAULT_DISPLAY_PAGES_NUM = 5;
    private final Page<T> page;
    private final int displayPagesNum;
    private final int paginationStartNum;
    private final int paginationEndNum;

    private PageDTO(Page<T> page, int displayPagesNum) {
        this.page = page;
        boolean isPaged = page.getPageable().isPaged();
        this.displayPagesNum = displayPagesNum;
        int currentPageNum = isPaged ? page.getPageable().getPageNumber() : 0;
        this.paginationStartNum = isPaged ? (currentPageNum / displayPagesNum) * displayPagesNum + 1 : 0;
        this.paginationEndNum = isPaged ? Math.min(paginationStartNum + displayPagesNum - 1, page.getTotalPages()) : 0;
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

    public List<T> getContent() {
        return page.getContent();
    }

    public int getSize() {
        return page.getSize();
    }

    public int getNumber() {
        return page.getNumber();
    }

    public boolean isFirst() {
        return page.isFirst();
    }

    public boolean isLast() {
        return page.isLast();
    }

    public int getTotalPages() {
        return page.getTotalPages();
    }

    public long getTotalElements() {
        return page.getTotalElements();
    }

    public int getPreviousPageNum() {
        return page.getPageable().isPaged() ? page.previousOrFirstPageable().getPageNumber() : -1;
    }

    public int getNextPageNum() {
        return page.getPageable().isPaged() ? page.nextOrLastPageable().getPageNumber() : -1;
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
