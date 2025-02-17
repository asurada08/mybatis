package com.example.mybatis.controller;

import com.example.mybatis.dao.BoardsDao;
import com.example.mybatis.dto.boards.BoardsDto;
import com.example.mybatis.dto.boards.WriteDto;
import com.example.mybatis.dto.members.MembersDto;
import com.example.mybatis.service.BoardService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class BoardsController {
    private final BoardsDao boardsDao;
    private final BoardService boardService;
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

    @GetMapping("/writeForm")
    public String writeForm() {
        MembersDto loginUser = (MembersDto) httpSession.getAttribute("loginUser");
        return "/boards/writeForm";
    }

    @PostMapping("/write")
    @ResponseBody
    public Map<String, Object> write(@RequestBody WriteDto writeDto) {
        System.out.println("controller - write call");
        Map<String, Object> response = boardService.write(writeDto);
        System.out.println(response);
        return response;
    }
}
