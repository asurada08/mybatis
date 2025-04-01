package com.example.mybatis.dao;

import com.example.mybatis.dto.boards.BoardsDto;
import com.example.mybatis.dto.boards.DetailDto;
import com.example.mybatis.dto.boards.MainListDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BoardsDao {
    public void insert(BoardsDto boardsDto);
//    public List<MainListDto> findAll();
    public List<MainListDto> getPagedPosts(@Param("startNum") int startNum, @Param("pageSize") int pageSize, @Param("keyword") String keyword, @Param("searchType") String searchType, Integer categoryId);
    public int getTotalItemCount(@Param("keyword") String keyword, @Param("searchType") String searchType, Integer categoryId);
    public BoardsDto findById(Integer id);
    public BoardsDto findByIdAndMembersId(Map<String, Object> params);
    public DetailDto findByBoardsId(@Param("boardsId") Integer boardsId, @Param("loginUserId") Integer loginUserId);
    public void update(BoardsDto boardsDto);
    public void delete(Integer id);
    public void viewCnt(Integer id);
}
