package com.sparta.finalprojectback.post.controller;

import com.sparta.finalprojectback.member.Member;
import com.sparta.finalprojectback.post.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class LikeController {

    private final LikeService likeService;


    @PostMapping("/user/plan/posts/{postId}/like")
    public Long createLike(@AuthenticationPrincipal Member member, @PathVariable Long postId) {
        return likeService.isLike(member, postId);
    }

    @DeleteMapping("/user/plan/posts/{postId}/like")
    public Long deleteLike(@AuthenticationPrincipal Member member, @PathVariable Long postId) {
        return likeService.isLike(member, postId);
    }
}
