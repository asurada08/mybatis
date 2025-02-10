package com.example.mybatis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardsController {

    @GetMapping("/main")
    public String board() {
        return "main";
    }
}
