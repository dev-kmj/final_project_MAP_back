package com.sparta.finalprojectback.post.service;

import com.sparta.finalprojectback.member.Member;
import com.sparta.finalprojectback.post.model.Likes;
import com.sparta.finalprojectback.post.model.Post;
import com.sparta.finalprojectback.post.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final PostService postService;

    // 좋아요
    @Transactional
    public Long isLike(Member member, Long postId) {

        Likes like = likeRepository.findByMemberIdAndPostId(member.getId(), postId)
                .orElseGet(() -> createLike(member, postId));

        Post post = like.getPost();

        if (like.toggle()) {
            // 좋아요 한 번 누르면 증가 (+1)
            post.updateLike(1);
        } else {
            // 좋아요 한 번 더 누르면 취소 (-1)
            post.updateLike(-1);
        }

        return postId;
    }

    public Likes createLike(Member member, Long postId) {

        Post post = postService.getPostById(postId);
        Likes like = new Likes(member, post);

        return likeRepository.save(like);
    }
}
