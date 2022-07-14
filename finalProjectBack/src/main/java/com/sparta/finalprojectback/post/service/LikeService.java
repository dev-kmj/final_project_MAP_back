package com.sparta.finalprojectback.post.service;

import com.sparta.finalprojectback.member.Member;
import com.sparta.finalprojectback.post.dto.LikeResponseDto;
import com.sparta.finalprojectback.post.model.Likes;
import com.sparta.finalprojectback.post.model.Post;
import com.sparta.finalprojectback.post.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final PostService postService;

    // 좋아요
    @Transactional
    public Long isLike(Member member, Long postId) {

        Optional<Likes> likePost = likeRepository.findByMemberIdAndPostId(member.getId(), postId);

        if (likePost.isEmpty()) {
            createLike(member, postId);
            likeRepository.findByMemberIdAndPostId(member.getId(), postId).get().getPost().updateLike(1);
        } else {
            likeRepository.findByMemberIdAndPostId(member.getId(), postId).get().getPost().updateLike(-1);
            likeRepository.deleteByMemberIdAndPostId(member.getId(), postId);
        }
        return postId;
    }

    public Likes createLike(Member member, Long postId) {

        Post post = postService.getPostById(postId);
        Likes like = new Likes(member, post);

        return likeRepository.save(like);
    }

}