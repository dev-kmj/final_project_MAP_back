package com.sparta.finalprojectback.community.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CommunityResponseDto {

    // 커뮤니티 아이디
    private Long postId;
    private String title;
    private String content;

    // 커뮤니티 게시물 작성자
    private String nickname;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;


    public CommunityResponseDto(Long postId, String title, String content, String nickname, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.nickname = nickname;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}