package com.sparta.finalprojectback.post.service;

import com.sparta.finalprojectback.member.Member;
import com.sparta.finalprojectback.post.dto.PostResponseDto;
import com.sparta.finalprojectback.post.dto.PostRequestDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;


public interface PostService {

    ResponseEntity<Long> createPost(Member member);
    ResponseEntity<String> deletePost(Member member, Long postId);
    ResponseEntity<String> updatePost(Member member, Long postId, PostRequestDto requestDto);
    ResponseEntity<List<PostResponseDto>> readMyPost(Member member);
    ResponseEntity<List<PostResponseDto>> readAllPost(boolean isComplete);
    // 이미지 업로드 api는 나중에 진행
<<<<<<< HEAD
    Post getPostById(Long opstId);
=======
>>>>>>> post_search
}
