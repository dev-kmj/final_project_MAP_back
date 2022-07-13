package com.sparta.finalprojectback.postComment.controller;

import com.sparta.finalprojectback.member.Member;
import com.sparta.finalprojectback.postComment.dto.PostCommentRequestDto;
import com.sparta.finalprojectback.postComment.dto.PostCommentResponseDto;
import com.sparta.finalprojectback.postComment.service.PostCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostCommentController {
    private final PostCommentService postCommentService;

    @PostMapping("/user/plan/post/{postId}/comment")
    public ResponseEntity<Long> createPostComment(@AuthenticationPrincipal Member member, @PathVariable Long postId, @RequestBody PostCommentRequestDto requestDto){
        return postCommentService.createPostComment(member, requestDto, postId);
    }

    @GetMapping("/user/plan/post/{postId}/comment")
    public ResponseEntity<List<PostCommentResponseDto>> readPostComment(@AuthenticationPrincipal Member member, @PathVariable Long postId){
        return postCommentService.readMyPostComment(member, postId);
    }


    @DeleteMapping("/user/plan/post/comment/{commentId}")
    public ResponseEntity<String> deletePostComment(@AuthenticationPrincipal Member member, @PathVariable Long commentId){
        return postCommentService.deletePostComment(member, commentId);
    }
}
