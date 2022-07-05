package com.sparta.finalprojectback.community.dto;

import com.sparta.finalprojectback.communitycomment.model.CommunityComment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class CommunityResponseDto {

    // 커뮤니티 아이디
    private Long id;
    private String title;
    private String content;

    // 커뮤니티 게시물 작성자
    private String writer;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;


    public CommunityResponseDto(Long id, String title, String content, String writer, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}