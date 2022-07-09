package com.sparta.finalprojectback.communitycomment.controller;

import com.sparta.finalprojectback.communitycomment.dto.CommunityCommentRequestDto;
import com.sparta.finalprojectback.communitycomment.dto.CommunityCommentResponseDto;
import com.sparta.finalprojectback.communitycomment.service.CommunityCommentService;
import com.sparta.finalprojectback.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommunityCommentController {

    private final CommunityCommentService communityCommentService;

    // 댓글 작성
    // 커뮤니티 게시물 id로 작성
    @PostMapping("/user/community/post/{postId}/comment")
    public ResponseEntity<Long> createCommunityComment(@PathVariable Long postId,
                                                       @RequestBody CommunityCommentRequestDto requestDto,
                                                       @AuthenticationPrincipal Member member) {


        return new ResponseEntity<>(communityCommentService.createComment(postId, requestDto, member), HttpStatus.OK);
    }


    // 댓글 조회
    @GetMapping("/user/community/post/{postId}/comment")
    public ResponseEntity<List<CommunityCommentResponseDto>> getCommunityCommentList(@PathVariable Long postId) {
        return new ResponseEntity<>(communityCommentService.getCommunityComments(postId), HttpStatus.OK);
    }


    // 댓글 삭제
    @DeleteMapping("/user/community/post/comment/{commentId}")
    public ResponseEntity<String> deleteCommunityComment(@PathVariable Long commentId, @AuthenticationPrincipal Member member) {
        communityCommentService.deleteCommunityComment(commentId, member);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

}