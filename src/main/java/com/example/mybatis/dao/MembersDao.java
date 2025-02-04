package com.example.mybatis.dao;

import com.example.mybatis.dto.LoginDto;
import com.example.mybatis.dto.MembersDto;

public interface MembersDao {
    public void insert(MembersDto membersDto);
    public MembersDto login(LoginDto loginDto);
    public MembersDto findByLogin_id(String login_id);
    public int idCheck(String login_id);
}
