package com.sparta.finalprojectback.post;
//요청하는 DTO

import lombok.Getter;
import lombok.RequiredArgsConstructor;
@Getter
@RequiredArgsConstructor
public class PostsRequestDto {
    private final String title;
    private final String imagePath;
    private final int views;
    private final String category;
    private final int likes;
    private final String content;
    private final boolean isComplete;
    private final int period;
}
