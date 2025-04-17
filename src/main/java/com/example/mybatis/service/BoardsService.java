package com.example.mybatis.service;

import com.example.mybatis.dao.BoardsDao;
import com.example.mybatis.dto.boards.*;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class BoardsService {

    private final BoardsDao boardsDao;
    private final HttpSession httpSession;

    public Map<String, Object> write(WriteDto writeDto){
        Map<String, Object> response = new HashMap<>();

        try {
            Integer loginUser = (Integer) httpSession.getAttribute("loginUser");

            if (loginUser == null) {
                response.put("code", 0);
                response.put("message", "게시글 작성 실패");
                return response;
            }

            boardsDao.insert(writeDto.toEntity(loginUser));
            response.put("code", 1);
            response.put("message", "게시글 작성 성공");

        } catch (Exception e) {
            e.printStackTrace();
            response.put("code", 0);
            response.put("message", "게시글 작성 중 오류 발생");
        }

        return response;
    }

    public PagingDto boardsList(int page, String keyword, String searchType, Integer categoryId) {
        try {
            int pageSize = 10;
            List<MainListDto> posts;

            int totalItems = boardsDao.getTotalItemCount(keyword, searchType, categoryId);
            PagingDto pagingDto = new PagingDto(page, pageSize, totalItems, searchType, keyword);
            pagingDto.PagingCal();

            posts = boardsDao.getPagedPosts(pagingDto.getStartNum(), pageSize, keyword, searchType, categoryId);
            pagingDto.setMainListDtos(posts);

            return pagingDto;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public DetailDto getDetail(Integer id, Integer loginUserId) {
        try {
            return boardsDao.findByBoardsId(id, loginUserId);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public BoardsDto updateForm(Integer id){
        try {
            Integer loginUser = (Integer) httpSession.getAttribute("loginUser");

            if (loginUser == null) {
                throw new RuntimeException("로그인 정보 없음");
            }

            Map<String, Object> params = new HashMap<>();
            params.put("id", id);
            params.put("members_id", loginUser);

            BoardsDto boardsDtoUpdate = boardsDao.findByIdAndMembersId(params);
            return boardsDtoUpdate;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Map<String, Object> update(Integer id, UpdateDto updateDto) {
        Map<String, Object> response = new HashMap<>();

        try {
            Integer loginUser = (Integer) httpSession.getAttribute("loginUser");

            Map<String, Object> params = new HashMap<>();
            params.put("id", id);
            params.put("members_id", loginUser);

            BoardsDto boardsDtoUpdate = boardsDao.findByIdAndMembersId(params);

            if (boardsDtoUpdate == null) {
                response.put("code", 0);
                response.put("message", "게시글이 존재하지 않습니다");
                return response;
            }

            boardsDtoUpdate.update(updateDto);
            boardsDao.update(boardsDtoUpdate);

            response.put("code", 1);
            response.put("data", boardsDtoUpdate);

        } catch (Exception e) {
            e.printStackTrace();
            response.put("code", 0);
            response.put("message", "게시글 수정 중 오류 발생");
        }
        return response;
    }

    public Map<String, Object> delete(Integer id) {
        Map<String, Object> response = new HashMap<>();

        try {
            Integer loginUser = (Integer) httpSession.getAttribute("loginUser");

            Map<String, Object> params = new HashMap<>();
            params.put("id", id);
            params.put("members_id", loginUser);

            BoardsDto boardsDtoDel = boardsDao.findByIdAndMembersId(params);

            if (boardsDtoDel == null) {
                response.put("code", 0);
                response.put("message", "게시글이 존재하지 않습니다");
                return response;
            }

            boardsDao.delete(id);

            response.put("code", 1);
            response.put("data", "게시글 삭제 성공");
        } catch (Exception e) {
            e.printStackTrace();
            response.put("code", 0);
            response.put("message", "게시글 삭제 중 오류 발생");
        }

        return response;
    }

    public void viewCnt(Integer id){
        try {
            boardsDao.viewCnt(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
