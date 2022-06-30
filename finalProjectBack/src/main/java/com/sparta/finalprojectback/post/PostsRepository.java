package com.sparta.finalprojectback.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostsRepository extends JpaRepository<Posts, Long> {
    List<Posts> findAllByOrderByModifiedAtDesc();
    List<Posts> findByUsername(String username);
    List<Posts> findByOrderByLikesAtDesc();
}