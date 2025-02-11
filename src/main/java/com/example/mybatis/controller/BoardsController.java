package com.example.mybatis.controller;

import com.example.mybatis.dto.MembersDto;
import com.example.mybatis.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class BoardsController {
    private final BoardService boardService;
    private final HttpSession httpSession;

    @GetMapping("/home")
    public String board(HttpSession session, Model model) {
        MembersDto loginUser = (MembersDto) session.getAttribute("loginUser");
        if (loginUser != null) {
            model.addAttribute("loginUser", loginUser);
        }
        return "boards/home";
    }
}
