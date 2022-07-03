package com.sparta.finalprojectback.post.service;

import com.sparta.finalprojectback.member.Member;
import com.sparta.finalprojectback.post.model.Post;
import com.sparta.finalprojectback.post.dto.PostRequestDto;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface PostService {

    ResponseEntity<Long> createPost(Member member);
    ResponseEntity<String> deletePost(Member member, Long postId);
    ResponseEntity<String> updatePost(Member member, Long postId, PostRequestDto requestDto);
    ResponseEntity<List<Post>> readMyPost(Member member);
    ResponseEntity<List<Post>> readAllPost();
    // 이미지 업로드 api는 나중에 진행


}
