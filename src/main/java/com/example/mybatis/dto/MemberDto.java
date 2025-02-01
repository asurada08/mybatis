package com.example.mybatis.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class MemberDto {
    private int id;
    private String login_id;
    private String password;
    private String nickname;
    private String del_yn;
    private String created_at;
    private String updated_at;
}
