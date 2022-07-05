package com.sparta.finalprojectback.community;

import com.sparta.finalprojectback.community.dto.CommunityRequestDto;
import com.sparta.finalprojectback.communitycomment.model.CommunityComment;
import com.sparta.finalprojectback.member.*;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    // 댓글리스트 추가코드
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<CommunityComment> communityComments = new ArrayList<>();

    // 커뮤니티 게시물 생성에 이용
    public Community(CommunityRequestDto requestDto, Member member) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.member = member;
    }

    // 커뮤니티 게시물 수정에 이용
    public void updateCommunity(Long id, CommunityRequestDto requestDto, Member member) {
        this.id = id;
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.member = member;
    }

    public void addComment(CommunityComment communityComment) {
        this.communityComments.add(communityComment);
    }
}