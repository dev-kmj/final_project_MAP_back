package com.sparta.finalprojectback.postComment.dto;

import com.sparta.finalprojectback.member.Member;
import com.sparta.finalprojectback.postComment.model.PostComment;
import lombok.Getter;

import java.util.List;

@Getter
public class PostCommentRequestDto {
    private Long postId;
    private String comment;
}
