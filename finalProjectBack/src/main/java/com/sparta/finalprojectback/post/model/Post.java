package com.sparta.finalprojectback.post.model;

import com.sparta.finalprojectback.member.Member;
import com.sparta.finalprojectback.member.Timestamped;
import lombok.*;

import javax.persistence.*;

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
    private String image;

    private int views;

    private Category category;

    private int likes;

    @Column(length = 300)
    private String content;

    private boolean isComplete;

    private int period;

    //FK
    @ManyToOne(fetch = FetchType.EAGER)
    private Member member;

    // 좋아요 매핑
    @OneToMany(
            mappedBy = "post",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List<Likes> likesList;

    public void updatePost(String title, Category category, int period, boolean isComplete) {
        this.title = title;
        this.category = category;
        this.period = period;
        this.isComplete = isComplete;
    }

    public void updateImage(String image){
        this.image = image;
    }

    // 좋아요 개수 증가/감소
    public void updateLike(int num) {
        likes += num;
    }
}