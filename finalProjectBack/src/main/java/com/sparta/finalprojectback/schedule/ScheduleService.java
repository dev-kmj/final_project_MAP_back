package com.sparta.finalprojectback.schedule;


import com.sparta.finalprojectback.post.Posts;
import com.sparta.finalprojectback.post.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final PostsRepository postsRepository;

    /* 일정 저장 */
//    @Transactional
//    public void createSchedule(Long postId, ScheduleRequestDto requestDto) {
//
////        Posts posts = postsRepository.findById(postId)
////                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다."));
//
//        Optional<Posts> optionalPosts = postsRepository.findById(postId);
//        if (!optionalPosts.isPresent()) {
//            throw new EntityNotFoundException("해당 아이디를 가진 게시물이 존재하지 않습니다.");
//        }
//        Posts posts = optionalPosts.get();
//
//        Schedule schedule = new Schedule(requestDto, posts);
//        scheduleRepository.save(schedule);
//    }

    public void createSchedule(ScheduleRequestDto requestDto) {
        Long postId = requestDto.getPosts().getId();
        Posts posts = postsRepository.findById(postId).get();
        Schedule schedule = new Schedule(requestDto, posts);
        scheduleRepository.save(schedule);
    }
}
