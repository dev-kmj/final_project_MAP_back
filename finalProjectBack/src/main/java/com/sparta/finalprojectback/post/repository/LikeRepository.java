package com.sparta.finalprojectback.post.repository;

import com.sparta.finalprojectback.post.model.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Likes, Long> {

    Optional<Likes> findByMemberIdAndPostId(Long memberId, Long postId);
}
