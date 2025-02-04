package com.example.mybatis.service;

import com.example.mybatis.dto.LoginDto;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import com.example.mybatis.dao.MembersDao;
import com.example.mybatis.dto.JoinDto;
import com.example.mybatis.dto.MembersDto;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MembersDao membersDao;

    public void join(JoinDto joinDto){
        MembersDto membersDto = joinDto.toEntity();
        membersDao.insert(membersDto);
    }

    public MembersDto login(LoginDto loginDto) {
        MembersDto membersDtoPW = membersDao.findByLogin_id(loginDto.getLogin_id());

        if(membersDtoPW == null){
            return null;
        }
        if(!(membersDtoPW.getPassword().equals(loginDto.getPassword()))){
            return null;
        }
        return membersDtoPW;
    }

    //public int idCheck(String login_id) throws Exception{
    //}

}
