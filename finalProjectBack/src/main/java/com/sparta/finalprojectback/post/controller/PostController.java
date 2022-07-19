package com.sparta.finalprojectback.post.controller;

import com.sparta.finalprojectback.member.Member;
import com.sparta.finalprojectback.post.dto.PostResponseDto;
import com.sparta.finalprojectback.post.dto.PostRequestDto;
import com.sparta.finalprojectback.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 여행 게시물 생성
    @PostMapping("/user/plan/post")
    public ResponseEntity<Long> createPost(@AuthenticationPrincipal Member member){
        return postService.createPost(member);
    }

    // 나의 여행 게시물 삭제
    @DeleteMapping("/user/plan/post/{postId}")
    public ResponseEntity<String> deletePost(@AuthenticationPrincipal Member member, @PathVariable Long postId){
        return postService.deletePost(member, postId);
    }

    // 나의 여행 게시물 수정
    @PutMapping("/user/plan/post/{postId}")
    public ResponseEntity<String> updatePost(@AuthenticationPrincipal Member member, @PathVariable Long postId, @RequestBody PostRequestDto requestDto) {
        return postService.updatePost(member, postId, requestDto);
    }

    // 나의 여행 게시물 조회
    @GetMapping("/user/plan/my-posts")
    public ResponseEntity<List<PostResponseDto>> readMyPost(@AuthenticationPrincipal Member member, boolean isComplete){
        postService.deleteEmptyPost(member, isComplete);
        return postService.readMyPost(member, isComplete);
    }

    // 모든 여행 게시물 조회
    @GetMapping("/plan/posts")
    public ResponseEntity<List<PostResponseDto>> readAllPost(boolean isComplete){
        return postService.readAllPost(isComplete);
    }

    //여행 게시물 정보 가져오기
    @GetMapping("/user/plan/post/{postId}")
    public ResponseEntity<PostResponseDto> readPostInfo(@PathVariable Long postId){
        return postService.readPostInfo(postId);
    }
    @PutMapping("/user/plan/post/{postId}/view")
    public ResponseEntity<PostResponseDto> countingView(@PathVariable Long postId){
        return postService.countingView(postId);
    }
    @GetMapping("/actuator/health") public ResponseEntity<String> actuator(){
        return new ResponseEntity("success", HttpStatus.OK); }

}
