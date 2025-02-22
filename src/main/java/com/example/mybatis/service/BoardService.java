package com.example.mybatis.service;

import com.example.mybatis.dao.BoardsDao;
import com.example.mybatis.dto.boards.*;
import com.example.mybatis.dto.members.MembersDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardsDao boardsDao;
    private final HttpSession httpSession;

    public Map<String, Object> write(WriteDto writeDto){
        System.out.println("service - write call");
        Map<String, Object> response = new HashMap<>();
        //  httpSession.getAttribute("loginUser"); -> 유저의 id값 ( Integer )
        // MembersDto에 이걸 집어넣게 짯음
        // 자바는 MembersDto 이거에 맞게 넣어야 하니까 일단 MembersDto를 띄움
        // httpSession.getAttribute("loginUser") 값을 넣기 위해서 캐스팅 해야함 ( 자바는 타입이 다르면 안들어감 )
        // Integer 값 넣으려고 MembersDto 라는걸 메모리에 집어넣고 숫자 하나 들고있음

        // Integer loginUser = httpSession.getAttribute("loginUser");
        // Integer loginUser = (Integer) httpSession.getAttribute("loginUser");

        MembersDto loginUser = (MembersDto) httpSession.getAttribute("loginUser"); //login_id 값만 가져오게 수정

        if (loginUser == null) {
            response.put("code", 0);
            response.put("message", "게시글 작성 실패");
        }

        boardsDao.insert(writeDto.toEntity(loginUser.getId())); //try catch
        response.put("code", 1);
        response.put("message", "게시글 작성 성공");

        return response;
    }

    public List<MainListDto> boardsList() {
        try{
            return boardsDao.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public DetailDto getDetail(Integer id, Integer loginUserId) {
        return boardsDao.findByBoardsId(id, loginUserId);
    }

    public BoardsDto updateForm(Integer id){
        MembersDto membersDto = (MembersDto) httpSession.getAttribute("loginUser");
        BoardsDto boardsDtoUpdate = boardsDao.findById(id);

        return boardsDtoUpdate;
    }

    public Map<String, Object> update(Integer id, UpdateDto updateDto) {
        System.out.println("B service - update call");
        MembersDto membersDto = (MembersDto) httpSession.getAttribute("loginUser");
        Map<String, Object> response = new HashMap<>();
        BoardsDto boardsDtoUpdate = boardsDao.findById(id);

        if (boardsDtoUpdate == null) {
            response.put("code", 0);
            response.put("message", "본인 글 만 수정 가능");
            return response;
        }

        boardsDtoUpdate.update(updateDto); //try
        boardsDao.update(boardsDtoUpdate);

        response.put("code", 1);
        response.put("data", boardsDtoUpdate);
        return response;
    }

    public Map<String, Object> delete(Integer id) {
        System.out.println("B service - delete call");
        Map<String, Object> response = new HashMap<>();
        BoardsDto boardsDtoDel = boardsDao.findById(id);

        if (boardsDtoDel == null) {
            response.put("code", 0);
            response.put("message", "회원탈퇴 실패");
            return response;
        }

        boardsDao.delete(id);

        response.put("code", 1);
        response.put("data", "회원탈퇴 성공");
        return response;
    }

    public void viewCnt(Integer id){
        BoardsDto boardsDto = boardsDao.findById(id);
        if(boardsDto != null) {
            boardsDto.viewCnt();
            boardsDao.save(boardsDto);
        }
    }
}
