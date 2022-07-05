package com.sparta.finalprojectback.communitycomment.dto;

import com.sparta.finalprojectback.communitycomment.model.CommunityComment;
import com.sparta.finalprojectback.member.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class CommunityCommentResponseDto extends Timestamped {

    private Long commentId;
    private String comment;

    // 댓글 작성자
    private String writer;

    // 댓글 작성시각
    private LocalDateTime createdAt;


    public CommunityCommentResponseDto(Long commentId, String comment, String writer, LocalDateTime createdAt) {
        this.commentId = commentId;
        this.comment = comment;
        this.writer = writer;
        this.createdAt = createdAt;
    }


}