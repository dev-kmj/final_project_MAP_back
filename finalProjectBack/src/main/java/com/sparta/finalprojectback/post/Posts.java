package com.sparta.finalprojectback.post;

import com.sparta.finalprojectback.member.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//PostsRepository에서 Setter 설정함
@NoArgsConstructor // 기본생성자를 만듭니다.
@Getter
@Entity // 테이블과 연계됨을 스프링에게 알려줍니다.
public class Posts extends Timestamped { // 생성,수정 시간을 자동으로 만들어줍니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String category;

    public Posts(String image, String title, String category) {
        this.image = image;
        this.title = title;
        this.category = category;
    }

    public Posts(PostsRequestDto requestDto, String username) {
        this.image = requestDto.getImage();
        this.title = requestDto.getTitle();
        this.category = requestDto.getCategory();
        this.username = username;
    }

    public void update(PostsRequestDto requestDto){
        this.image = requestDto.getImage();
        this.title = requestDto.getTitle();
        this.category = requestDto.getCategory();
    }
}