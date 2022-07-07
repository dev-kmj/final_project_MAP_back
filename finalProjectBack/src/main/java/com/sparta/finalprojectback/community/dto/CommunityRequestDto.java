package com.sparta.finalprojectback.community.dto;

import com.sparta.finalprojectback.community.model.Community;
import com.sparta.finalprojectback.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommunityRequestDto {

    private String title;
    private String content;

}