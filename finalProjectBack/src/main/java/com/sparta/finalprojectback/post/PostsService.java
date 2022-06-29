package com.sparta.finalprojectback.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    // 스프링에게 이 클래스는 서비스임을 명시
    // final: 서비스에게 꼭 필요한 녀석임을 명시
    private final PostsRepository postsRepository;

    @Transactional // SQL 쿼리가 일어나야 함을 스프링에게 알려줌
    public Long update(Long id, PostsRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );
        posts.update(requestDto);
        return id;
    }
}
