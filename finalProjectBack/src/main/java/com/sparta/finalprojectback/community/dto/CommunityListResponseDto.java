package com.sparta.finalprojectback.community.dto;

import com.sparta.finalprojectback.community.model.Community;
import lombok.Getter;

@Getter
public class CommunityListResponseDto {

    // 목록 조회시 사용할 dto (제목, 닉네임만 필요함)

    private String title;
    private String nickname;

    public CommunityListResponseDto(Community community) {
        this.title = community.getTitle();
        this.nickname = community.getMember().getNickname();
    }
}
