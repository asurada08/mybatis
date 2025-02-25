package com.example.mybatis.service;

import com.example.mybatis.dto.members.LoginDto;
import com.example.mybatis.dto.members.UpdateDto;
import com.example.mybatis.dto.members.JoinDto;
import com.example.mybatis.dto.members.MembersDto;
import com.example.mybatis.dao.MembersDao;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MembersDao membersDao;

    public Map<String, Object> login(LoginDto loginDto) {
        System.out.println("service - login call");
        Map<String, Object> response = new HashMap<>();

        try {
            MembersDto membersDtoLogin = membersDao.login(loginDto.getLogin_id(), loginDto.getPassword());

            if (membersDtoLogin == null) {
                response.put("status", "fail");
                response.put("message", "아이디 또는 비밀번호를 확인해주세요");
                return response;
            }

            response.put("status", "success");
            response.put("loginUser", membersDtoLogin);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("status", "fail");
            response.put("message", "로그인 중 오류 발생");
        }

        return response;
    }

    public int idCheck(MembersDto membersDto) throws Exception{
        int result = membersDao.idCheck(membersDto);
        return result;
    }

    public Map<String, Object> join(JoinDto joinDto){

        System.out.println("service - join call");
        MembersDto membersDto = joinDto.toEntity();

        Map<String, Object> response = new HashMap<>();

        String result = "fall";

        int memberId = -1;

        try {
            memberId = membersDao.insert(membersDto);
        } catch (Exception e) {
            System.out.println(e);
        }

        if(memberId == 1) {
            result = "ok";
        }
        response.put("result", result);

        return response;
    }

    public MembersDto updateForm(Integer id){
        try {
            MembersDto membersDtoUpdate = membersDao.findById(id);

            if (membersDtoUpdate == null) {
                return null;
            }

            return membersDtoUpdate;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Map<String, Object> update(Integer id, UpdateDto updateDto){
        System.out.println("service - update call");
        Map<String, Object> response = new HashMap<>();
        try {
            MembersDto membersDtoUpdate = membersDao.findById(id);

            if (membersDtoUpdate == null) {
                response.put("code", 0);
                return response;
            }

            membersDtoUpdate.update(updateDto);
            membersDao.update(membersDtoUpdate);

            response.put("code", 1);
            response.put("data", membersDtoUpdate);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("code", 0);
        }
        return response;
    }

    public Map<String, Object> delete(Integer id) {
        System.out.println("service - delete call");
        Map<String, Object> response = new HashMap<>();
        try {
            MembersDto membersDtoDel = membersDao.findById(id);

            if (membersDtoDel == null) {
                response.put("code", 0);
                response.put("message", "존재하지 않는 회원입니다");
                return response;
            }

            membersDao.delete(id);

            response.put("code", 1);
            response.put("data", "회원탈퇴 성공");
        } catch (Exception e) {
            e.printStackTrace();
            response.put("code", 0);
            response.put("message", "회원탈퇴 중 오류 발생");
        }

        return response;
    }

}
