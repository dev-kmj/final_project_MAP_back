package com.sparta.finalprojectback.community;

import com.sparta.finalprojectback.member.*;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Community extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", nullable = false)
    private Long id;

    @Column(length = 500, nullable = false) // 길이 255 -> 500으로 늘려줌
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)    // varchar(255) 제한 없애주기
    private String content;

    // 유저아이디 (작성자)
    @Column(nullable = false)
    private String username;


    // 커뮤니티 게시물 생성에 이용
    public Community(CommunityRequestDto requestDto, String username) {
        this.username = username;
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }

    // 커뮤니티 게시물 수정에 이용
    public void updateCommunity(Long id, CommunityRequestDto requestDto) {
        this.id = id;
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }
}

