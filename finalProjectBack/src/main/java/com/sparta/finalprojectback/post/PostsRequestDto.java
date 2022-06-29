package com.sparta.finalprojectback.post;
//요청하는 DTO

import lombok.Getter;
import lombok.RequiredArgsConstructor;
@Getter
@RequiredArgsConstructor
public class PostsRequestDto {
    private final String title;
    private final String image;
    private final String category;
}
