package com.sparta.finalprojectback.schedule.postComment.dto;

import lombok.Getter;

@Getter
public class PostCommentRequestDto {
    private Long postId;
    private String comment;
}
