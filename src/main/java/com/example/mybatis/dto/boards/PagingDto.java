package com.example.mybatis.dto.boards;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PagingDto {
    private int pageNumber;
    private int pageSize;
    private int totalItems;
    private int totalPages;
    private boolean hasPrevious;
    private boolean hasNext;
    private int startNum;
//    private int endNum;
    private List<MainListDto> mainListDtos;

    private String keyword;
    private String searchType;

    public PagingDto(int pageNumber, int pageSize, int totalItems, String searchType, String keyword) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalItems = totalItems;
        this.searchType = searchType;
        this.keyword = keyword;
    }

    public void PagingCal() {
        this.totalPages = (int) Math.ceil((double) totalItems / pageSize);
        this.hasPrevious = pageNumber > 1;
        this.hasNext = pageNumber < totalPages;

        if (pageNumber < 1) {
            pageNumber = 1;
        }
        if (pageNumber > totalPages) {
            pageNumber = totalPages;
        }

        if (pageNumber > 0) {
            this.startNum = (pageNumber - 1) * pageSize;
        } else {
            this.startNum = 0;
        }
    }
}
