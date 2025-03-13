package com.example.mybatis.controller;

import com.example.mybatis.dao.BoardsDao;
import com.example.mybatis.dto.boards.BoardsDto;
import com.example.mybatis.dto.boards.UpdateDto;
import com.example.mybatis.dto.boards.WriteDto;
import com.example.mybatis.dto.comments.CommentsDto;
import com.example.mybatis.dto.comments.CommentsListDto;
import com.example.mybatis.dto.comments.CommentsUpdateDto;
import com.example.mybatis.dto.comments.CommentsWriteDto;
import com.example.mybatis.dto.members.MembersDto;
import com.example.mybatis.service.BoardService;
import com.example.mybatis.service.CommentsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class BoardsController {
    private final BoardsDao boardsDao;
    private final BoardService boardService;
    private final CommentsService commentsService;
    private final HttpSession httpSession;

    @GetMapping({"/", "/home"})
    public String board(HttpSession session, Model model) {
        MembersDto loginUser = (MembersDto) session.getAttribute("loginUser");
        if (loginUser != null) {
            model.addAttribute("loginUser", loginUser);
        }
        System.out.println("controller - list call");
        model.addAttribute("boardsList", boardService.boardsList());
        return "boards/home";
    }

    @GetMapping("/boards/writeForm")
    public String writeForm() {
        MembersDto loginUser = (MembersDto) httpSession.getAttribute("loginUser");
        return "/boards/writeForm";
    }

    @PostMapping("/boards/write")
    @ResponseBody
    public Map<String, Object> write(@RequestBody WriteDto writeDto) {
        System.out.println("controller - write call");
        Map<String, Object> response = boardService.write(writeDto);
        System.out.println(response);
        return response;
    }

    @GetMapping("/boards/detail/{id}")
    public String getDetail(@PathVariable Integer id, Model model){
        MembersDto loginUser = (MembersDto) httpSession.getAttribute("loginUser");
        if(loginUser == null) {
            model.addAttribute("detailDto", boardService.getDetail(id, 0));
        } else {
            model.addAttribute("detailDto", boardService.getDetail(id, loginUser.getId()));
        }

        List<CommentsListDto> commentsList = commentsService.getComments(id);
        model.addAttribute("comments", commentsList);

        return "/boards/detail";
    }

    @GetMapping("/boards/updateForm/{id}")
    public String updateForm(@PathVariable Integer id, Model model){
        BoardsDto boardsDtoUpdate = boardService.updateForm(id);
        model.addAttribute("boardsDtoUpdate", boardsDtoUpdate);
        return "/boards/updateForm";
    }

    @PostMapping("/boards/update/{id}")
    @ResponseBody
    public Map<String, Object> update(@PathVariable Integer id, @RequestBody UpdateDto updateDto){
        System.out.println("B controller - update call id : " + id);
        Map<String, Object> response = boardService.update(id, updateDto);
        return response;
    }

    @PostMapping("/boards/delete/{id}")
    @ResponseBody
    public Map<String, Object> delete(@PathVariable Integer id, HttpServletRequest request) {
        System.out.println("B controller - delete call");
        Map<String, Object> response = boardService.delete(id);
        return response;
    }

    @GetMapping("/boards/viewCnt/{id}")
    @ResponseBody
    public void viewCnt(@PathVariable Integer id) {
        boardService.viewCnt(id);
    }

    @PostMapping("/boards/writeComment")
    @ResponseBody
    public Map<String, Object> writeComments(@RequestBody CommentsWriteDto commentsWriteDto) {
        System.out.println("controller - write call");
        Map<String, Object> response = commentsService.writeComments(commentsWriteDto);
        System.out.println(commentsWriteDto);
        System.out.println(response);
        return response;
    }

    @PostMapping("/boards/writeReply")
    @ResponseBody
    public Map<String, Object> writeReply(@RequestBody CommentsWriteDto commentsWriteDto) {
        System.out.println("controller - write call");
        Map<String, Object> response = commentsService.writeReply(commentsWriteDto);
        System.out.println(response);
        return response;
    }

    @PostMapping("/boards/updateComments/{id}")
    @ResponseBody
    public Map<String, Object> updateComments(@PathVariable Integer id, @RequestBody CommentsUpdateDto commentsUpdateDto){
        System.out.println("controller - updateComments call");
        System.out.println("Received ID: " + id);
        System.out.println("Received comment content: " + commentsUpdateDto.getContent());
        Map<String, Object> response = commentsService.updateComments(id, commentsUpdateDto);
        return response;
    }

    @PostMapping("/boards/deleteComments")
    @ResponseBody
    public Map<String, Object> deleteComments(@PathVariable Integer id, HttpServletRequest request) {
        System.out.println("controller - deleteComments call");
        Map<String, Object> response = commentsService.deleteComments(id);
        return response;
    }
}
