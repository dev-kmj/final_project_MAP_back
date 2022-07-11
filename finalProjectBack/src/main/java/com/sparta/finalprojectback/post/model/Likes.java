package com.sparta.finalprojectback.post.model;

import com.sparta.finalprojectback.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @Column(nullable = false)
    private boolean isLike;

    public Likes(Member member, Post post) {
        this.member = member;
        this.post = post;
        this.isLike = false;
    }

    public Boolean toggle() {
        isLike = !isLike;
        return isLike;
    }
}