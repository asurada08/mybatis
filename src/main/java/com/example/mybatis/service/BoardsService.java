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
        System.out.println("service - write call");
        Map<String, Object> response = new HashMap<>();

        try {
            //dto 잡아먹는거 다시 확인
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
            //없는거 검색 했을때 처리
            int pageSize = 10;

            List<MainListDto> posts;

            System.out.println("-----1");
            int totalItems = boardsDao.getTotalItemCount(keyword, searchType, categoryId);
            System.out.println("-----2");

            System.out.println(totalItems);

            System.out.println("-----3");
            PagingDto pagingDto = new PagingDto(page, pageSize, totalItems, searchType, keyword);
            System.out.println("-----4");

            pagingDto.PagingCal();
            System.out.println("-----5");

            System.out.println(pagingDto.getStartNum());
            System.out.println(pageSize);

            posts = boardsDao.getPagedPosts(pagingDto.getStartNum(), pageSize, keyword, searchType, categoryId);
            System.out.println("-----6");

            pagingDto.setMainListDtos(posts);
            System.out.println("-----7");

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
        System.out.println("B service - update call");
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

    //BoardsDto boardsDtoDel = boardsDao.findByIdAndMembersId(id, membersDto.getId());
    //수정 참고

    public Map<String, Object> delete(Integer id) {
        System.out.println("B service - delete call");
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
