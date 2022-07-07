package com.sparta.finalprojectback.community.dto;

import com.sparta.finalprojectback.community.model.Community;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommunityResponseDto {

    // 커뮤니티 아이디
    private Long postId;
    private String title;
    private String content;

    // 커뮤니티 게시물 작성자
    private String nickname;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;


    public CommunityResponseDto(Community community) {
        this.postId = community.getId();
        this.title = community.getTitle();
        this.content = community.getContent();
        this.nickname = community.getMember().getNickname();
        this.createdAt = community.getCreatedAt();
        this.modifiedAt = community.getModifiedAt();
    }
}