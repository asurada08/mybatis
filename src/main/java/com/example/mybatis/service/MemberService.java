package com.example.mybatis.service;


import com.example.mybatis.dao.MembersDao;
import com.example.mybatis.dto.JoinDto;
import com.example.mybatis.dto.MembersDto;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    // 멤버 저장하기
    public void join(JoinDto joinDto) {
        MembersDto membersDto = new MembersDto();
        membersDto.setLogin_id(joinDto.getLogin_id());
        membersDto.setPassword(joinDto.getPassword());
        membersDto.setNickname(joinDto.getNickname());
        MembersDao.insert(membersDto);
    }


}
