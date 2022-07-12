package com.sparta.finalprojectback.schedule.postComment.repository;

import com.sparta.finalprojectback.schedule.postComment.model.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostCommentRepository extends JpaRepository<PostComment, Long> {
    List<PostComment> findAllByPost_Id(Long post);
    void deleteAllByPost_Id(Long postId);
}
