package com.sparta.finalprojectback.community.controller;

import com.sparta.finalprojectback.community.dto.CommunityResponseDto;
import com.sparta.finalprojectback.community.dto.CommunityRequestDto;
import com.sparta.finalprojectback.community.service.CommunityService;
import com.sparta.finalprojectback.communitycomment.service.CommunityCommentService;
import com.sparta.finalprojectback.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommunityController {

    private final CommunityService communityService;
    private final CommunityCommentService communityCommentService;

    // 커뮤니티 게시물 작성
//    @PostMapping("/user/community/post")
//    public ResponseEntity<Long> createCommunity(@RequestBody CommunityRequestDto requestDto, @AuthenticationPrincipal Member member) {
//        return new ResponseEntity<Long>(communityService.createCommunity(requestDto, member), HttpStatus.OK);
//    }

    // 수정
//    @PutMapping("/user/community/post/{postId}")
//    public ResponseEntity<String> updateCommunity(@PathVariable Long postId, @RequestBody CommunityRequestDto requestDto, @AuthenticationPrincipal Member member) {
//        communityService.updateCommunity(postId, requestDto, member);
//        return new ResponseEntity<>("success", HttpStatus.OK);
//    }

    // 조회
//    @GetMapping("/user/community/posts")
//    public ResponseEntity<List<CommunityResponseDto>> getCommunityList() {
//       return new ResponseEntity<>(communityService.getCommunityList(), HttpStatus.OK);
//    }

    // 내 게시물 조회
//    @GetMapping("/user/community/my-posts")
//    public ResponseEntity<List<CommunityResponseDto>> getMyCommunityList(@AuthenticationPrincipal Member member) {
//        return new ResponseEntity<>(communityService.getMyCommunityList(member), HttpStatus.OK);
//    }


    // 게시물 상세 조회
//    @GetMapping("/user/community/post/{postId}")
//    public ResponseEntity<CommunityResponseDto> getCommunityDetail(@PathVariable Long postId) {
//        return new ResponseEntity<>(communityService.communityDetail(postId), HttpStatus.OK);
//    }


    // 삭제
    @DeleteMapping("/user/community/post/{postId}")
    public ResponseEntity<String> deleteCommunity(@PathVariable Long postId, @AuthenticationPrincipal Member member) {
        communityCommentService.deleteAllCommunityComments(postId);
        communityService.deleteCommunity(postId, member);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }



    /**
     * 리팩토링 진행중
     */

    @PostMapping("/user/community/post")
    public Long createCommunity(@RequestBody CommunityRequestDto requestDto, @AuthenticationPrincipal Member member) {
        return communityService.createCommunity(requestDto, member);
    }

    // 전체 조회 (제목 + 내용)
    @GetMapping("/user/community/posts")
    public List<CommunityResponseDto> getCommunityList(@AuthenticationPrincipal Member member) {
        return communityService.getCommunityList();
    }

    // 페이징 처리 (url 다름 /paging)
    @GetMapping("/user/community/posts/paging")
    public Page<CommunityResponseDto> getAllCommunities(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sortBy") String sortBy,
            @RequestParam("isAsc") boolean isAsc
    ) {
        return communityService.getAllCommunities(page, size, sortBy, isAsc);
    }







}

