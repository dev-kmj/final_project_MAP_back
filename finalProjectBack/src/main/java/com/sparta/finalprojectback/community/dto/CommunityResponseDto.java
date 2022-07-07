package com.sparta.finalprojectback.community.dto;

import com.sparta.finalprojectback.community.model.Community;
import lombok.Builder;
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

    public CommunityResponseDto(Community community) {
        postId = community.getId();
        title = community.getTitle();
        content = community.getContent();
        nickname = community.getMember().getNickname();
        createdAt = community.getCreatedAt();
        modifiedAt = community.getModifiedAt();
    }

}