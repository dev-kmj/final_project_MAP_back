package com.sparta.finalprojectback.post;

import com.sparta.finalprojectback.member.Timestamped;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

//PostsRepository에서 Setter 설정함
@NoArgsConstructor // 기본생성자를 만듭니다.
@AllArgsConstructor
@Getter
@Entity // 테이블과 연계됨을 스프링에게 알려줍니다.
public class Posts extends Timestamped { // 생성,수정 시간을 자동으로 만들어줍니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column(length = 45)
    private String title;
    @Column(length = 200)
    private String imagePath;
    @Column()
    private int views;
    @Column()
    private String category;
    @Column()
    private int likes;
    @Column(length = 300)
    private String content;
    @Column()
    private boolean isComplete;
    @Column()
    private int period;

//    public Posts(String username, String title, String imagePath, int views, String category, int like, String content, boolean isComplete, int period) {
//        this.username = username;
//        this.title = title;
//        this.imagePath = imagePath;
//        this.views = views;
//        this.category = category;
//        this.like = like;
//        this.content = content;
//        this.isComplete = isComplete;
//        this.period = period;
//    }

//    public Posts(PostsRequestDto requestDto, String username) {
//        this.title = requestDto.getTitle();
//        this.imagePath = requestDto.getImagePath();
//        this.views = requestDto.getViews();
//        this.category = requestDto.getCategory();
//        this.like = requestDto.getLike();
//        this.content = requestDto.getContent();
//        this.isComplete = requestDto.isComplete();
//        this.period = requestDto.getPeriod();
//        this.username = username;
//    }

    public Posts(String username){
        this.username = username;
    }
    public void update(PostsRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.imagePath = requestDto.getImagePath();
        this.views = requestDto.getViews();
        this.category = requestDto.getCategory();
        this.likes = requestDto.getLikes();
        this.content = requestDto.getContent();
        this.isComplete = requestDto.isComplete();
        this.period = requestDto.getPeriod();
    }
}