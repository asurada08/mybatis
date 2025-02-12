package com.example.mybatis.dao;

import com.example.mybatis.dto.LoginDto;
import com.example.mybatis.dto.MembersDto;

public interface MembersDao {
    public int insert(MembersDto membersDto);
    public MembersDto login(String login_id, String password);
    public int idCheck(MembersDto membersDto);
}
