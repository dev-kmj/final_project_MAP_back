package com.sparta.finalprojectback.schedule.postComment.service;

import com.sparta.finalprojectback.member.Member;
import com.sparta.finalprojectback.schedule.postComment.dto.PostCommentRequestDto;
import com.sparta.finalprojectback.schedule.postComment.dto.PostCommentResponseDto;
import com.sparta.finalprojectback.schedule.postComment.model.PostComment;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface PostCommentService {
    ResponseEntity<List<PostComment>> createPostComment(Member member, PostCommentRequestDto requestDto);
    ResponseEntity<String> deletePostComment(Member member, Long commentId);
    ResponseEntity<List<PostCommentResponseDto>> readMyPostComment(Member member, Long postId);
}