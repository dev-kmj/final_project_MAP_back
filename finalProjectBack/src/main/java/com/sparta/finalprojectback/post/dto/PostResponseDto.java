package com.sparta.finalprojectback.post.dto;

import com.sparta.finalprojectback.post.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
public class PostResponseDto {
    private Long id;
    private String title;
    private String content;
    private Category category;
    private String nickname;
    private LocalDateTime createdAt;

}
