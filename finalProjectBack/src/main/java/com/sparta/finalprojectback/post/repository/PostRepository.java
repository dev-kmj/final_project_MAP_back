package com.sparta.finalprojectback.post.repository;

import com.sparta.finalprojectback.post.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByMember_Id(Long postId);
    List<Post> findPostsByIsComplete(boolean isComplete);
}
