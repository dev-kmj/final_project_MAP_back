package com.sparta.finalprojectback.communitycomment.controller;

import com.sparta.finalprojectback.communitycomment.dto.CommunityCommentRequestDto;
import com.sparta.finalprojectback.communitycomment.dto.CommunityCommentResponseDto;
import com.sparta.finalprojectback.communitycomment.repository.CommunityCommentRepository;
import com.sparta.finalprojectback.communitycomment.service.CommunityCommentService;
import com.sparta.finalprojectback.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommunityCommentController {

    private final CommunityCommentRepository communityCommentRepository;
    private final CommunityCommentService communityCommentService;

    // 댓글 작성
    // 커뮤니티 게시물 id로 작성
    @PostMapping("/user/community/post/{communityId}/comment")
    public ResponseEntity<Long> createCommunityComment(@PathVariable Long communityId,
                                                       @RequestBody CommunityCommentRequestDto requestDto,
                                                       @AuthenticationPrincipal Member member) {

        return new ResponseEntity<>(communityCommentService.createComment(communityId, requestDto, member), HttpStatus.OK);
    }


    // 댓글 조회
    @GetMapping("/user/community/post/{communityId}/comment")
    public ResponseEntity<List<CommunityCommentResponseDto>> getCommunityCommentList(@PathVariable Long communityId) {
        return new ResponseEntity<>(communityCommentService.getCommunityComments(communityId), HttpStatus.OK);
    }


    // 댓글 삭제
    @DeleteMapping("/user/community/post/comment/{id}")
    public ResponseEntity<String> deleteCommunityComment(@PathVariable Long id, @AuthenticationPrincipal Member member) {
        communityCommentService.deleteCommunityComment(id, member);
        return new ResponseEntity<String>("success", HttpStatus.OK);
    }

}