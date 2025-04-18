package com.example.mybatis.dto.members;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinDto {
    private String login_id;
    private String password;
    private String nickname;

    public MembersDto toEntity() {
        MembersDto membersDto = new MembersDto(this.login_id, this.password, this.nickname);
        return membersDto;
    }
}
