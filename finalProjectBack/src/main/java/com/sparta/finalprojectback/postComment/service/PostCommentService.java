package com.sparta.finalprojectback.postComment.service;

import com.sparta.finalprojectback.member.Member;
import com.sparta.finalprojectback.postComment.dto.PostCommentRequestDto;
import com.sparta.finalprojectback.postComment.dto.PostCommentResponseDto;
import com.sparta.finalprojectback.postComment.model.PostComment;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface PostCommentService {
    ResponseEntity<List<PostComment>> createPostComment(Member member, PostCommentRequestDto requestDto);
    ResponseEntity<String> deletePostComment(Member member, Long commentId);
    ResponseEntity<List<PostCommentResponseDto>> readMyPostComment(Member member, Long postId);
}