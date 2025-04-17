package com.example.mybatis.controller;

import com.example.mybatis.dao.MembersDao;
import com.example.mybatis.dto.members.JoinDto;
import com.example.mybatis.dto.members.LoginDto;
import com.example.mybatis.dto.members.MembersDto;
import com.example.mybatis.dto.members.UpdateDto;
import com.example.mybatis.service.MembersService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@Controller
public class MembersController {

    private final MembersService membersService;

    @GetMapping("/loginForm")
    public String loginForm(){
        return "member/loginForm";
    }

    @PostMapping("/login")
    @ResponseBody
    public Map<String, Object> login(@RequestBody LoginDto loginDto, HttpSession httpSession){
        Map<String, Object> response = membersService.login(loginDto);

        if (response != null && response.containsKey("status") && response.get("status").equals("success")){
            Integer loginUser = (Integer) response.get("loginUser");
            String nickname = (String) response.get("nickname");

            httpSession.setAttribute("loginUser", loginUser);
            httpSession.setAttribute("nickname", nickname);
        }

        System.out.println(response);
        return response;
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute("loginUser");
            session.invalidate();
        }
        return "redirect:/home";
    }

    @GetMapping("/joinForm")
    public String joinForm(){
        return "member/joinForm";
    }

    @PostMapping("/join")
    @ResponseBody
    public Map<String, Object> join(@RequestBody JoinDto joinDto){
        Map<String, Object> response = membersService.join(joinDto);
        return response;
    }

    @GetMapping("/idCheck")
    @ResponseBody
    public int idCheck(@RequestParam("login_id") String login_id) throws Exception{
        System.out.println(login_id);
        MembersDto membersDto = new MembersDto();
        membersDto.setLogin_id(login_id);
        int result = membersService.idCheck(membersDto);
        return result;
    }

    @GetMapping("/updateForm/{id}")
    public String updateForm(@PathVariable Integer id, Model model){
        MembersDto membersDtoUpdate = membersService.updateForm(id);
        model.addAttribute("updateUserData", membersDtoUpdate);
        return "member/updateForm";
    }

    @PostMapping("/update/{id}")
    @ResponseBody
    public Map<String, Object> update(@PathVariable Integer id, @RequestBody UpdateDto updateDto){
        Map<String, Object> response = membersService.update(id, updateDto);

        return response;
    }

    @PostMapping("/delete/{id}")
    @ResponseBody
    public Map<String, Object> delete(@PathVariable Integer id, HttpServletRequest request){
        Map<String, Object> response = membersService.delete(id);

        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        return response;
    }
}
