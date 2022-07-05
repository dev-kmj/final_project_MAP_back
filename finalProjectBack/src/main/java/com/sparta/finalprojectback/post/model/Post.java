package com.sparta.finalprojectback.post.model;

import com.sparta.finalprojectback.member.Member;
import com.sparta.finalprojectback.member.Timestamped;
import com.sparta.finalprojectback.postComment.model.PostComment;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//PostsRepository에서 Setter 설정함
@NoArgsConstructor // 기본생성자를 만듭니다.
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity // 테이블과 연계됨을 스프링에게 알려줍니다.
public class Post extends Timestamped { // 생성,수정 시간을 자동으로 만들어줍니다.

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(length = 45)
    private String title;

    @Column(length = 200)
    private String imagePath;

    private int views;

    private Category category;

    private int likes;

    @Column(length = 300)
    private String content;

    private boolean isComplete;

    private int period;

    //FK
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    public void updatePost(String title, Category category, String content, boolean isComplete) {
        this.title = title;
        this.category = category;
        this.content = content;
        this.isComplete = isComplete;
    }
}