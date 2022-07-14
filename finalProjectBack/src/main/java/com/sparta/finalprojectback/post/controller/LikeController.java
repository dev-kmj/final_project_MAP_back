package com.sparta.finalprojectback.post.controller;

import com.sparta.finalprojectback.member.Member;
import com.sparta.finalprojectback.post.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class LikeController {

    private final LikeService likeService;


    // 좋아요 기능 (취소도 가능)
    @PostMapping("/user/plan/post/{postId}/like")
    public Long createLike(@AuthenticationPrincipal Member member, @PathVariable Long postId) {
        return likeService.isLike(member, postId);
    }

}