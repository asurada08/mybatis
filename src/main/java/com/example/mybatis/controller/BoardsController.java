package com.example.mybatis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardsController {

    @GetMapping("/board")
    public String board() {
        return "board";
    }
}
