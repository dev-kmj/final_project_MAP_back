package com.sparta.finalprojectback.communitycomment.dto;

import com.sparta.finalprojectback.member.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class CommunityCommentResponseDto extends Timestamped {

    private Long commentId;
    private String comment;

    // 댓글 작성자
    private String nickname;

    // 댓글 작성시각
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @CreatedDate
    private LocalDateTime createdAt;

    public CommunityCommentResponseDto(Long commentId, String comment, String nickname, LocalDateTime createdAt) {
        this.commentId = commentId;
        this.comment = comment;
        this.nickname = nickname;
        this.createdAt = createdAt;
    }
}