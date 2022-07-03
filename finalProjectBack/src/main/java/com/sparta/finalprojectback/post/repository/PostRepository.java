package com.sparta.finalprojectback.post.repository;

import com.sparta.finalprojectback.post.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    public List<Post> findAllByMember_Id(Long memberId);
}
