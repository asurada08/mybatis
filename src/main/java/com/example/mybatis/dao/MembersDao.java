package com.example.mybatis.dao;

import com.example.mybatis.dto.members.MembersDto;

public interface MembersDao {
    public int insert(MembersDto membersDto);
    public MembersDto login(String login_id, String password);
    public int idCheck(MembersDto membersDto);
    public MembersDto findById(Integer id);
    public void update(MembersDto membersDto);
    public void delete(Integer id);

}
